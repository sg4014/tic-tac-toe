package tictactoe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Board {

    private final CellValue[][] table;

    Board() {
        this.table = getBlankTable();
    }

    /**
     * Prints the board in its current state
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

    /**
     * Returns true if the cell is not BLANK
     */
    public boolean isCellFilled(int row, int column) {
        return getCellValue(row, column) != CellValue.BLANK;
    }

    /**
     * Returns the value at the given position on the board;
     * Note: the position is 1-based (from 1 to 3)
     */
    public CellValue getCellValue(int row, int column) {
        return table[row - 1][column - 1];
    }


    /**
     * Fills the cell at the given coordinates with the given value.
     */
    public void fillCellWith(int row, int column, CellValue value) {
        table[row - 1][column - 1] = value;
    }

    /**
     * Returns true if there are no blank cells on the board
     */
    public boolean isFull() {
        for (CellValue[] row : table) {
            if (Arrays.stream(row).anyMatch(v -> v == CellValue.BLANK)) {
                return false;
            }
        }

        return true;
    }

    /**
     * Returns true if the given value occurs on the board
     * three times in a horizontal, vertical or diagonal row.
     */
    public boolean isThreeInRow(CellValue value) {
        return isThreeInHorizontalRow(value)
                || isThreeInVerticalRow(value)
                || isThreeInDiagonalRow(value);
    }

    /**
     * Returns coordinates of every blank cell on the board.
     * Coordinates of each cell are represented
     * as an array of two integers (row and column position).
     */
    public ArrayList<int[]> getPossibleMoves() {
        ArrayList<int[]> possibleMoves = new ArrayList<>();

        for (int row = 1; row < 4; row++) {
            for (int column = 1; column < 4; column++) {
                if (isCellFilled(row, column)) {
                    continue;
                }

                possibleMoves.add(new int[]{row, column});
            }
        }

        return possibleMoves;
    }

    private boolean isThreeInHorizontalRow(CellValue value) {
        for (CellValue[] horizontalRow : table) {
            if (Arrays.stream(horizontalRow)
                    .allMatch(cv -> cv == value)) {
                return true;
            }
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

}
