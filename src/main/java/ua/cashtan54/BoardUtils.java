package ua.cashtan54;

import java.util.*;
import java.util.stream.Collectors;

class BoardUtils {
    static Map<Integer, Integer> getCellsPossibleNumbersCounter(List<Cell> cells) {
        final Map<Integer, Integer> result = new TreeMap<>();
        for (Cell cell : cells) {
            for (Integer possibleNumber : cell.getPossibleNumbers()) {
                result.putIfAbsent(possibleNumber, 0);
                result.put(possibleNumber, result.get(possibleNumber) + 1);
            }
        }
        return result;
    }

    static Collection<Integer> getIntegersFromCells(List<Cell> listOfCells) {
        return listOfCells.stream().map(Cell::getNumber).filter(num -> num > 0).collect(Collectors.toSet());
    }
}
