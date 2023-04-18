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

        int[] coordinates = new int[2];

        while (board.isCellFilled(coordinates)) {
            coordinates[0] = random.nextInt(3) + 1;
            coordinates[1] = random.nextInt(3) + 1;
        }

        System.out.println("Making move level \"easy\"");
        board.fillCell(coordinates);
    }

    private int[] moveToWinInstantly(Board board) {
        return new int[2];
    }
}
