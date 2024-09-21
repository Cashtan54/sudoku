package ua.cashtan54;

import java.util.List;
import java.util.Optional;

class OnlyOnePossibleValueProcessor implements CellsProcessor{
    @Override
    public boolean process(List<List<Cell>> board) {
        for (List<Cell> listCells : board) {
            final Optional<Cell> optionalCell = listCells.
                    stream().
                    filter(c -> c.getPossibleNumbers().size() == 1).
                    findFirst();
            optionalCell.ifPresent(cell -> cell.setNumber(cell.getPossibleNumbers().iterator().next()));
            if (optionalCell.isPresent()) {
                return true;
            }
        }
        return false;
    }
}
