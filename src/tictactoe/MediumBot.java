package tictactoe;

import java.util.Random;

public class MediumBot extends Player {

    public MediumBot(CellValue moveSymbol) {
        super(moveSymbol);
    }
    @Override
    public void makeNextMove(Board board) {
        Random random = new Random();

        /*
        moveToWinInstantly = getMoveToWinInstantly()

        if moveToWinInstantly != null:
            return moveToWinInstantly

        opponentMoveToWinInstantly = getOpponentMoveToWinInstantly()

        if opponentMoveToWinInstantly != null:
            return opponentMoveToWinInstantly

        else:
            makeRandomMove()
         */

        int row = 1;
        int column = 1;

        while (board.isCellFilled(row, column)) {
            row = random.nextInt(3) + 1;
            column = random.nextInt(3) + 1;
        }

        System.out.println("Making move level \"easy\"");
        board.fillCell(row, column);
    }

    private int[] getMoveToWinInstantly(Board board) {
        // move with horizontal row
        if (board.getCellValue(1, 1) == moveSymbol
                && board.getCellValue(1, 2) == moveSymbol
                && !board.isCellFilled(1, 3)) {
            return new int[]{1, 3};
        }

        return new int[2];
        // move with vertical row

        // move with diagonal row
    }
}
