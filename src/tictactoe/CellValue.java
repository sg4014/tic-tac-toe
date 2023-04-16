package tictactoe;

public enum CellValue {
    X, O, BLANK;

    @Override
    public String toString() {
        if (this == BLANK) {
            return " ";
        }

        return name();
    }
}
