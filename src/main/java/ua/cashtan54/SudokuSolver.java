package ua.cashtan54;

import java.util.*;
import java.util.concurrent.*;


public class SudokuSolver {
    private final List<List<Cell>> rows;
    private final List<List<Cell>> columns;
    private final List<List<Cell>> squares;
    private final List<CellsProcessor> processors =new ArrayList<>();

    public static SudokuSolver fromListOfStrings(List<String> strings) {
        final List<List<Integer>> resultValue = strings.
                stream().
                map(s -> List.of(s.split(" "))).
                map(SudokuSolver::getIntegersFromCellsValues).
                toList();
        return new SudokuSolver(resultValue);
    }

    public void solve() {
        final List<Callable<Boolean>> tasks = getTasks();
        boolean taskSolvingResult;
        do {
            reviewPossibleNumbers();
            taskSolvingResult = runSolvingTasks(tasks);
        } while (taskSolvingResult);
        if (!isSolved()) {
            System.out.println(this);
            throw new CanNotSolveException("Sudoku is not solved(");
        }
    }

    public List<List<Integer>> getSolved() {
        return rows.
                stream().
                map(cells -> cells.stream().map(Cell::getNumber).toList()).
                toList();
    }

    @Override
    public String toString() {
        final StringBuilder resultString = new StringBuilder();
        rows.forEach(row -> resultString.append(row.toString()).append("\n"));
        return resultString.toString().trim();
    }

    public boolean isSolved() {
        return !rows.
                stream().
                flatMap(row -> row.stream().map(Cell::isNumberSet))
                .filter(Boolean::valueOf).
                toList().
                isEmpty();
    }

    private SudokuSolver(List<List<Integer>> integers) {
        rows = createRows(integers);
        columns = createColumns(rows);
        squares = createSquares(rows);
        registerProcessor(new OnlyOnePossibleValueProcessor());
        registerProcessor(new PossibleValueOnlyOnceProcessor());
    }

    private boolean runSolvingTasks(List<Callable<Boolean>> solvingTasks) {
        for (Callable<Boolean> task : solvingTasks) {
            try {
                boolean taskResult = task.call();
                if (taskResult) {
                    return true;
                }
            } catch (Exception e) {
                throw new CanNotSolveException("Something wrong", e);
            }
        }
        return false;
    }

    private void reviewPossibleNumbers() {
        final CellsProcessor processor = new DeleteImpossibleNumbersProcessor();
        processor.process(rows);
        processor.process(columns);
        processor.process(squares);
    }

    private List<Callable<Boolean>> getTasks() {
        final List<Callable<Boolean>> result = new ArrayList<>();
        for (CellsProcessor processor : processors) {
            result.add(() -> processor.process(rows));
            result.add(() -> processor.process(columns));
            result.add(() -> processor.process(squares));
        }
        return result;
    }

    private static List<Integer> getIntegersFromCellsValues(List<String> cellsValues) {
        return cellsValues.stream().map(cellValue -> {
            if (cellValue.matches("[1-9]*")) {
                return Integer.parseInt(cellValue);
            }
            return 0;
        }).toList();
    }

    private List<List<Cell>> createRows(List<List<Integer>> integers) {
        final List<List<Cell>> result = new ArrayList<>();
        for (List<Integer> listOfIntegers : integers) {
            final List<Cell> row = listOfIntegers.stream().map(Cell::fromValue).toList();
            result.add(row);
        }
        return result;
    }

    private List<List<Cell>> createColumns(List<List<Cell>> rows) {
        final List<List<Cell>> result = new ArrayList<>();
        for (int i = 0; i < rows.size(); i ++) {
            result.add(new ArrayList<>());
        }
        for (int row = 0; row < rows.size(); row++) {
            for (int col = 0; col < rows.size(); col++) {
                result.get(col).add(rows.get(row).get(col));
            }
        }
        return result;
    }

    private List<List<Cell>> createSquares(List<List<Cell>> rows) {
        final List<List<Cell>> result = new ArrayList<>();

        for (int i = 0; i < rows.size(); i ++) {
            result.add(new ArrayList<>());
        }
        for (int row = 0; row < rows.size(); row++) {
            for (int col = 0; col < rows.size(); col++) {
                int squareRow = row / 3;
                int squareCol = col / 3;
                int squareIndex = squareRow * 3 + squareCol;
                result.get(squareIndex).add(rows.get(row).get(col));
            }
        }
        return result;
    }

    private void registerProcessor(CellsProcessor processor) {
        processors.add(processor);
    }
}
