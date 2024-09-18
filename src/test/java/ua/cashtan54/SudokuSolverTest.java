package ua.cashtan54;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

class SudokuSolverTest {
    // Sudoku examples from https://sudoku.com/

    @Test
    public void shouldSolveEasy() {
        // given
        List<String> initialSudoku = new ArrayList<>();
        initialSudoku.add("* 7 5 * * * * 2 *");
        initialSudoku.add("3 6 9 2 * * 7 * *");
        initialSudoku.add("* * 8 * 3 5 1 6 9");
        initialSudoku.add("6 * * * 1 3 * * *");
        initialSudoku.add("* * * 6 * * * * 5");
        initialSudoku.add("* 9 4 * 7 * 6 * *");
        initialSudoku.add("4 5 * * * * 3 8 7");
        initialSudoku.add("7 * 6 * 5 * 4 * 2");
        initialSudoku.add("* * 2 3 4 7 * 1 *");
        initialSudoku.forEach(System.out::println);

        List<List<Integer>> expected = new ArrayList<>();
        expected.add(Stream.of(1, 7, 5, 4, 6, 9, 8, 2, 3).toList());
        expected.add(Stream.of(3, 6, 9, 2, 8, 1, 7, 5, 4).toList());
        expected.add(Stream.of(2, 4, 8, 7, 3, 5, 1, 6, 9).toList());
        expected.add(Stream.of(6, 2, 7, 5, 1, 3, 9, 4, 8).toList());
        expected.add(Stream.of(8, 1, 3, 6, 9, 4, 2, 7, 5).toList());
        expected.add(Stream.of(5, 9, 4, 8, 7, 2, 6, 3, 1).toList());
        expected.add(Stream.of(4, 5, 1, 9, 2, 6, 3, 8, 7).toList());
        expected.add(Stream.of(7, 3, 6, 1, 5, 8, 4, 9, 2).toList());
        expected.add(Stream.of(9, 8, 2, 3, 4, 7, 5, 1, 6).toList());

        // when
        SudokuSolver sudokuSolver = SudokuSolver.fromListOfStrings(initialSudoku);
        sudokuSolver.solve();
        List<List<Integer>> actual = sudokuSolver.getSolved();

        // then
        System.out.println(sudokuSolver);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldSolveHard() {
        // given
        List<String> initialSudoku = new ArrayList<>();
        initialSudoku.add("* 4 * * * * * * 6");
        initialSudoku.add("* * * 9 * 2 4 * *");
        initialSudoku.add("* 5 6 4 * 7 9 * *");
        initialSudoku.add("* * * 2 * * * * *");
        initialSudoku.add("5 9 * * * * 6 1 *");
        initialSudoku.add("1 3 2 * 5 * * * *");
        initialSudoku.add("* 2 7 * 8 6 * * *");
        initialSudoku.add("9 1 5 * * * * * *");
        initialSudoku.add("6 * * * 7 * 5 2 *");
        initialSudoku.forEach(System.out::println);

        List<List<Integer>> expected = new ArrayList<>();
        expected.add(List.of(3, 4, 9, 8, 1, 5, 2, 7, 6));
        expected.add(List.of(8, 7, 1, 9, 6, 2, 4, 3, 5));
        expected.add(List.of(2, 5, 6, 4, 3, 7, 9, 8, 1));
        expected.add(List.of(7, 6, 4, 2, 9, 1, 3, 5, 8));
        expected.add(List.of(5, 9, 8, 7, 4, 3, 6, 1, 2));
        expected.add(List.of(1, 3, 2, 6, 5, 8, 7, 4, 9));
        expected.add(List.of(4, 2, 7, 5, 8, 6, 1, 9, 3));
        expected.add(List.of(9, 1, 5, 3, 2, 4, 8, 6, 7));
        expected.add(List.of(6, 8, 3, 1, 7, 9, 5, 2, 4));

        // when
        SudokuSolver sudokuSolver = SudokuSolver.fromListOfStrings(initialSudoku);
        sudokuSolver.solve();
        List<List<Integer>> actual = sudokuSolver.getSolved();

        // then
        System.out.println(sudokuSolver);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldSolveExpert() {
        // given
        List<String> initialSudoku = new ArrayList<>();
        initialSudoku.add("9 * * 4 * * * * *");
        initialSudoku.add("* * * 6 * * 1 * 7");
        initialSudoku.add("* 8 7 9 1 5 3 * 2");
        initialSudoku.add("4 * * * * 2 * 8 *");
        initialSudoku.add("* 6 * 5 * * 7 * *");
        initialSudoku.add("5 * * 7 6 * * 1 *");
        initialSudoku.add("* * 5 * * * * 2 *");
        initialSudoku.add("* * * * * 9 8 * 1");
        initialSudoku.add("* 9 * 2 * * * * 3");
        initialSudoku.forEach(System.out::println);

        List<List<Integer>> expected = new ArrayList<>();
        expected.add(List.of(9, 1, 3, 4, 2, 7, 5, 6, 8));
        expected.add(List.of(2, 5, 4, 6, 8, 3, 1, 9, 7));
        expected.add(List.of(6, 8, 7, 9, 1, 5, 3, 4, 2));
        expected.add(List.of(4, 7, 9, 1, 3, 2, 6, 8, 5));
        expected.add(List.of(1, 6, 2, 5, 9, 8, 7, 3, 4));
        expected.add(List.of(5, 3, 8, 7, 6, 4, 2, 1, 9));
        expected.add(List.of(3, 4, 5, 8, 7, 1, 9, 2, 6));
        expected.add(List.of(7, 2, 6, 3, 4, 9, 8, 5, 1));
        expected.add(List.of(8, 9, 1, 2, 5, 6, 4, 7, 3));

        // when
        SudokuSolver sudokuSolver = SudokuSolver.fromListOfStrings(initialSudoku);
        sudokuSolver.solve();
        List<List<Integer>> actual = sudokuSolver.getSolved();

        // then
        System.out.println(sudokuSolver);
        Assertions.assertEquals(expected, actual);
    }
}