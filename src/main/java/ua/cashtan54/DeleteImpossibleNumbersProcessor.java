package ua.cashtan54;

import java.util.List;

import static ua.cashtan54.BoardUtils.getIntegersFromCells;

class DeleteImpossibleNumbersProcessor implements CellsProcessor {
    @Override
    public boolean process(List<List<Cell>> board) {
        board.forEach(
                cells -> cells.forEach(
                        cell -> cell.removeFromPossibleNumbers(getIntegersFromCells(cells))
                ));
        return false;
    }
}