package tictactoe;

import java.util.Random;

public class MediumBot extends Player {

    public MediumBot(CellValue moveSymbol) {
        super(moveSymbol);
    }

    @Override
    public void makeNextMove(Board board) {
        /* make a winning move if there is such */
        int[] moveToWinInstantly = getMoveToWinInstantly(board, moveSymbol);

        if (moveToWinInstantly != null) {
            makeMove(board, moveToWinInstantly);
            return;
        }

        /* prevent the opponent's winning move if there is such */
        CellValue opponentMoveSymbol = (moveSymbol == CellValue.X)
                ? CellValue.O
                : CellValue.X;

        int[] opponentMoveToWinInstantly =
                getMoveToWinInstantly(board, opponentMoveSymbol);

        if (opponentMoveToWinInstantly != null) {
            makeMove(board, opponentMoveToWinInstantly);
            return;
        }

        makeRandomMove(board);
    }

    private int[] getMoveToWinInstantly(Board board, CellValue moveSymbol) {
        for (int i = 1; i < 4; i++) {
            for (int k = 1; k < 4; k++) {
                if (board.isCellFilled(i, k)) {
                    continue;
                }

                board.fillCellWith(i, k, moveSymbol);

                if (board.isThreeInRow(moveSymbol)) {
                    board.fillCellWith(i, k, CellValue.BLANK);
                    return new int[]{i, k};
                }

                board.fillCellWith(i, k, CellValue.BLANK);
            }
        }

        return null;
    }

    private void makeMove(Board board, int[] coordinates) {
        System.out.println("Making move level \"medium\"");
        int row = coordinates[0];
        int column = coordinates[1];
        board.fillCellWith(row, column, moveSymbol);
    }

    private void makeRandomMove(Board board) {
        Random random = new Random();

        int row = random.nextInt(3) + 1;
        int column = random.nextInt(3) + 1;

        while (board.isCellFilled(row, column)) {
            row = random.nextInt(3) + 1;
            column = random.nextInt(3) + 1;
        }

        System.out.println("Making move level \"medium\"");
        board.fillCellWith(row, column, moveSymbol);
    }
}
