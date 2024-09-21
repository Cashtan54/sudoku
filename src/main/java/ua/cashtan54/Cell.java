package ua.cashtan54;

import java.util.*;

class Cell {
    private int number;
    private final Set<Integer> possibleNumbers;

    static Cell fromValue(Integer value) {
        return new Cell(value);
    }

    @Override
    public String toString() {
        if (number > 0) {
            return String.valueOf(number);
        }
        return possibleNumbers.toString();
    }

    boolean isNumberSet() {
        return number != 0;
    }

    int getNumber() {
        return number;
    }

    final void setNumber(int number) {
        this.number = number;
        possibleNumbers.clear();
    }

    Set<Integer> getPossibleNumbers() {
        return possibleNumbers;
    }

    void removeFromPossibleNumbers(Set<Integer> numbers) {
        possibleNumbers.removeAll(numbers);
    }

    private Cell(Integer value) {
        possibleNumbers = new HashSet<>();
        if (value.equals(0)) {
            Collections.addAll(possibleNumbers, 1, 2, 3, 4, 5, 6, 7, 8, 9);
        } else {
            setNumber(value);
        }
    }
}
