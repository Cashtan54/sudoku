package ua.cashtan54;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

class SudokuSolverTest {
    // Sudoku examples from https://sudoku.com/

    @ParameterizedTest(name = "{2}")
    @MethodSource("provideTestData")
    public void shouldSolveSudoku(List<String> initial, List<List<Integer>> expected, String difficulty) {
        initial.forEach(System.out::println);
        // when
        SudokuSolver sudokuSolver = SudokuSolver.fromListOfStrings(initial);
        sudokuSolver.solve();
        List<List<Integer>> actual = sudokuSolver.getSolved();

        // then
        System.out.println(sudokuSolver);
        Assertions.assertEquals(expected, actual);
    }

    static Stream<Arguments> provideTestData() {
        List<String> easyInitialSudoku = List.of(
                "* 7 5 * * * * 2 *",
                "3 6 9 2 * * 7 * *",
                "* * 8 * 3 5 1 6 9",
                "6 * * * 1 3 * * *",
                "* * * 6 * * * * 5",
                "* 9 4 * 7 * 6 * *",
                "4 5 * * * * 3 8 7",
                "7 * 6 * 5 * 4 * 2",
                "* * 2 3 4 7 * 1 *"
        );
        List<List<Integer>> easyExpected = List.of(
                List.of(1, 7, 5, 4, 6, 9, 8, 2, 3),
                List.of(3, 6, 9, 2, 8, 1, 7, 5, 4),
                List.of(2, 4, 8, 7, 3, 5, 1, 6, 9),
                List.of(6, 2, 7, 5, 1, 3, 9, 4, 8),
                List.of(8, 1, 3, 6, 9, 4, 2, 7, 5),
                List.of(5, 9, 4, 8, 7, 2, 6, 3, 1),
                List.of(4, 5, 1, 9, 2, 6, 3, 8, 7),
                List.of(7, 3, 6, 1, 5, 8, 4, 9, 2),
                List.of(9, 8, 2, 3, 4, 7, 5, 1, 6)
        );

        List<String> hardInitialSudoku = List.of(
                "* 4 * * * * * * 6",
                "* * * 9 * 2 4 * *",
                "* 5 6 4 * 7 9 * *",
                "* * * 2 * * * * *",
                "5 9 * * * * 6 1 *",
                "1 3 2 * 5 * * * *",
                "* 2 7 * 8 6 * * *",
                "9 1 5 * * * * * *",
                "6 * * * 7 * 5 2 *"
        );
        List<List<Integer>> hardExpected = List.of(
                List.of(3, 4, 9, 8, 1, 5, 2, 7, 6),
                List.of(8, 7, 1, 9, 6, 2, 4, 3, 5),
                List.of(2, 5, 6, 4, 3, 7, 9, 8, 1),
                List.of(7, 6, 4, 2, 9, 1, 3, 5, 8),
                List.of(5, 9, 8, 7, 4, 3, 6, 1, 2),
                List.of(1, 3, 2, 6, 5, 8, 7, 4, 9),
                List.of(4, 2, 7, 5, 8, 6, 1, 9, 3),
                List.of(9, 1, 5, 3, 2, 4, 8, 6, 7),
                List.of(6, 8, 3, 1, 7, 9, 5, 2, 4)
        );

        List<String> expertInitialSudoku = List.of(
                "9 * * 4 * * * * *",
                "* * * 6 * * 1 * 7",
                "* 8 7 9 1 5 3 * 2",
                "4 * * * * 2 * 8 *",
                "* 6 * 5 * * 7 * *",
                "5 * * 7 6 * * 1 *",
                "* * 5 * * * * 2 *",
                "* * * * * 9 8 * 1",
                "* 9 * 2 * * * * 3"
        );
        List<List<Integer>> expertExpected = List.of(
                List.of(9, 1, 3, 4, 2, 7, 5, 6, 8),
                List.of(2, 5, 4, 6, 8, 3, 1, 9, 7),
                List.of(6, 8, 7, 9, 1, 5, 3, 4, 2),
                List.of(4, 7, 9, 1, 3, 2, 6, 8, 5),
                List.of(1, 6, 2, 5, 9, 8, 7, 3, 4),
                List.of(5, 3, 8, 7, 6, 4, 2, 1, 9),
                List.of(3, 4, 5, 8, 7, 1, 9, 2, 6),
                List.of(7, 2, 6, 3, 4, 9, 8, 5, 1),
                List.of(8, 9, 1, 2, 5, 6, 4, 7, 3)
        );
        return Stream.of(
                Arguments.of(easyInitialSudoku, easyExpected, "easy"),
                Arguments.of(hardInitialSudoku, hardExpected, "hard"),
                Arguments.of(expertInitialSudoku, expertExpected, "expert")
        );
    }
}