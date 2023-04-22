package tictactoe;

public abstract class Player {
    protected final CellValue moveSymbol;

    Player(CellValue moveSymbol) {
        if (moveSymbol == CellValue.BLANK) {
            throw new IllegalArgumentException("Illegal move symbol: BLANK. "
                    + "Expected X or O.");
        }
        this.moveSymbol = moveSymbol;
    }

    public abstract void makeNextMove(Board board);
}
