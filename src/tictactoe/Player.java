package tictactoe;

public abstract class Player {
    protected final CellValue moveSymbol;

    Player(CellValue moveSymbol) {
        this.moveSymbol = moveSymbol;
    }

    public abstract void makeNextMove(Board board);
}
