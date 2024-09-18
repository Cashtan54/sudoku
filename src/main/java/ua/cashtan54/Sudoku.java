package ua.cashtan54;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Sudoku {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello");
        final String fileName = getFromConsole("Give me file in puzzles folder with sudoku");
        final List<String> dataFromFile = readFromFile(fileName);
        final SudokuSolver sudokuSolver = SudokuSolver.fromListOfStrings(dataFromFile);

        final long startTime = System.currentTimeMillis();

        sudokuSolver.solve();
        System.out.println(sudokuSolver);

        final long endTime = System.currentTimeMillis();
        final long duration = endTime - startTime;
        System.out.println("Sudoku was solved in: " + duration + " milliseconds");
    }

    private static String getFromConsole(String askMessage) {
        System.out.println(askMessage);
        final BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));

        try {
            return reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static List<String> readFromFile(String fileName) throws FileNotFoundException {
        final List<String> resultStrings = new ArrayList<>();
        final File file = new File("puzzles/" + fileName);
        try (Scanner myReader = new Scanner(file)) {
            while (myReader.hasNextLine()) {
                resultStrings.add(myReader.nextLine());

            }
        }
        return resultStrings;
    }
}