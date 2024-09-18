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
            if (optionalCell.isPresent()) {
                final Cell cell = optionalCell.get();
                final int possibleNumber = cell.getPossibleNumbers().iterator().next();
                cell.setNumber(possibleNumber);
                return true;
            }
        }
        return false;
    }
}
