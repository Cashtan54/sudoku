package ua.cashtan54;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static ua.cashtan54.BoardUtils.getCellsPossibleNumbersCounter;

class PossibleValueOnlyOnceProcessor implements CellsProcessor{
    @Override
    public boolean process(List<List<Cell>> board) {
        for (List<Cell> cells : board) {
            final Map<Integer, Integer> possibleNumbersCounter = getCellsPossibleNumbersCounter(cells);
            final Optional<Integer> optionalNumberWithSingleAppearance = possibleNumbersCounter.
                    keySet().
                    stream().
                    filter(k -> possibleNumbersCounter.get(k).equals(1)).
                    findFirst();
            int key;
            if (optionalNumberWithSingleAppearance.isPresent()){
                key = optionalNumberWithSingleAppearance.get();
            } else {
                continue;
            }
            for (Cell cell : cells) {
                if (cell.getPossibleNumbers().contains(key)) {
                    cell.setNumber(key);
                    return true;
                }
            }
        }
        return false;
    }
}
