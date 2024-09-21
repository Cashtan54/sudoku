package ua.cashtan54;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

import static ua.cashtan54.BoardUtils.getCellsPossibleNumbersCounter;

class PossibleValueOnlyOnceProcessor implements CellsProcessor{
    @Override
    public boolean process(List<List<Cell>> board) {
        for (List<Cell> cells : board) {
            final Map<Integer, Integer> possibleNumbersCounter = getCellsPossibleNumbersCounter(cells);
            final AtomicBoolean somethingChanged = new AtomicBoolean(false);
            possibleNumbersCounter.
                    keySet().
                    stream().
                    filter(k -> possibleNumbersCounter.get(k).equals(1)).
                    findFirst().
                    ifPresent(key -> cells.
                            stream().
                            filter(cell -> cell.getPossibleNumbers().contains(key)).
                            forEach(cell -> {
                                cell.setNumber(key);
                                somethingChanged.set(true);
                            }));
            if (somethingChanged.get()) {
                return true;
            }
        }
        return false;
    }
}
