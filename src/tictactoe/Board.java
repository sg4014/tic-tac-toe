package tictactoe;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Board {

    private CellValue[][] table;
    private CellValue nextCellValue;
    private int emptyCellsLeft;

    Board() {
        this.table = getBlankTable();
        this.nextCellValue = CellValue.X;
        this.emptyCellsLeft = 9;
    }

    /**
     * prints the board in its current state
     */
    public void print() {
        System.out.println("---------");

        for (CellValue[] row : table) {
            System.out.println("| " + Arrays.stream(row)
                    .map(CellValue::toString)
                    .collect(Collectors.joining(" ")) + " |");

        }

        System.out.println("---------");
    }

    public boolean isCellFilled(int row, int column) {
        return getCellValue(row, column) != CellValue.BLANK;
    }

    /**
     * returns the value at the given position on the board;
     * Note: the position is 1-based (from 1 to 3)
     */
    public CellValue getCellValue(int row, int column) {
        return table[row - 1][column - 1];
    }

    public void fillCell(int row, int column) {
        /* subtract 1 since coordinates are 1-based, and index is 0-based */
        table[row - 1][column - 1] = nextCellValue;

        updateNextCellValue();
        emptyCellsLeft--;
    }

    public boolean isFull() {
        return emptyCellsLeft == 0;
    }

    public boolean isThreeInRow(CellValue value) {
        return isThreeInHorizontalRow(value)
                || isThreeInVerticalRow(value)
                || isThreeInDiagonalRow(value);
    }

    private boolean isThreeInHorizontalRow(CellValue value) {
        for (CellValue[] horizontalRow : table) {
            if (Arrays.stream(horizontalRow)
                    .allMatch(cv -> cv == value)) {
                return true;
            }
            ;
        }

        return false;
    }

    private static CellValue[][] getBlankTable() {
        CellValue[][] blankTable = new CellValue[3][3];

        for (CellValue[] row : blankTable) {
            Arrays.fill(row, CellValue.BLANK);
        }

        return blankTable;
    }

    private boolean isThreeInVerticalRow(CellValue value) {
        outer:
        for (int colIndex = 0; colIndex < 3; colIndex++) {

            // iterate over column
            for (int rowIndex = 0; rowIndex < 3; rowIndex++) {
                CellValue cv = table[rowIndex][colIndex];

                if (!(cv.equals(value))) {
                    continue outer; // check next column
                }
            }

            return true;
        }

        return false;
    }

    private boolean isThreeInDiagonalRow(CellValue value) {
        return isThreeInMainDiagonal(value)
                || isThreeInCounterDiagonal(value);
    }

    private boolean isThreeInMainDiagonal(CellValue value) {
        for (int i = 0; i < 3; i++) {
            CellValue cv = table[i][i];

            if (!(cv.equals(value))) {
                return false;
            }
        }

        return true;
    }

    private boolean isThreeInCounterDiagonal(CellValue value) {
        for (int i = 0; i < 3; i++) {
            CellValue cv = table[2 - i][i];

            if (!(cv.equals(value))) {
                return false;
            }
        }

        return true;
    }

    private void updateNextCellValue() {
        if (nextCellValue == CellValue.X) {
            nextCellValue = CellValue.O;
        } else {
            nextCellValue = CellValue.X;
        }
    }
}
