package ua.cashtan54;

import java.util.Collection;
import java.util.List;

import static ua.cashtan54.BoardUtils.getIntegersFromCells;

class DeleteImpossibleNumbersProcessor implements CellsProcessor {
    @Override
    public boolean process(List<List<Cell>> board) {
        for (List<Cell> listCells : board) {
            final Collection<Integer> setIntegers = getIntegersFromCells(listCells);
            listCells.forEach(cell -> cell.removeFromPossibleNumbers(setIntegers));
            }
        return false;
    }
}