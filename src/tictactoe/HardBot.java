package tictactoe;

import java.util.ArrayList;
import java.util.Arrays;

public class HardBot extends Player {

    private final CellValue opponentMoveSymbol;

    public HardBot(CellValue moveSymbol) {
        super(moveSymbol);
        this.opponentMoveSymbol = (moveSymbol == CellValue.X)
                ? CellValue.O
                : CellValue.X;
    }

    @Override
    public void makeNextMove(Board board) {
        int[] nextMove = getBestMove(board);
        makeMove(board, nextMove);
    }

    private int[] getBestMove(Board board) {
        ArrayList<int[]> possibleMoves = board.getPossibleMoves();

        int maxScore = -1;
        int[] bestMoveSoFar = possibleMoves.get(0);

        for (int[] move : possibleMoves) {
            Board copyOfBoard = board.getDeepCopy();
            copyOfBoard.fillCellWith(move, moveSymbol);

            int score = minimax(copyOfBoard, true);

            if (score == 1) {
                return move;
            }

            if (score > maxScore) {
                bestMoveSoFar = move;
                maxScore = score;
            }
        }

        return bestMoveSoFar;
    }


    private int minimax(Board board, boolean isMaximizingPlayer) {
        if (isMaximizingPlayer) {
            if (board.isThreeInRow(moveSymbol)) {
                return 1;
            } else if (board.isFull()) {
                return 0;
            }

            Board[] nextBoards = generateNextBoards(board, opponentMoveSymbol);

            return getMinScore(nextBoards);
        }

        if (board.isThreeInRow(opponentMoveSymbol)) {
            return -1;
        } else if (board.isFull()) {
            return 0;
        }

        Board[] nextBoards = generateNextBoards(board, moveSymbol);

        return getMaxScore(nextBoards);
    }

    private Board[] generateNextBoards(Board board, CellValue nextMoveSymbol) {
        ArrayList<int[]> possibleMoves = board.getPossibleMoves();
        Board[] nextBoards = new Board[possibleMoves.size()];

        for (int i = 0; i < nextBoards.length; i++) {
            int[] nextMove = possibleMoves.get(i);
            Board nextBoard = board.getDeepCopy();
            nextBoard.fillCellWith(nextMove, nextMoveSymbol);
            nextBoards[i] = nextBoard;
        }

        return nextBoards;
    }

    private int getMaxScore(Board[] boards) {
        int maxScore = -1;

        for (Board board : boards) {
            int score = minimax(board, true);

            if (score == 1) {
                return 1;
            }

            maxScore = Math.max(score, maxScore);
        }

        return maxScore;
    }

    private int getMinScore(Board[] boards) {
        int minScore = 1;

        for (Board board : boards) {
            int score = minimax(board, false);

            if (score == -1) {
                return -1;
            }

            minScore = Math.min(score, minScore);
        }

        return minScore;
    }

    private void makeMove(Board board, int[] coordinates) {
        System.out.println("Making move level \"hard\"");
        board.fillCellWith(coordinates, moveSymbol);
    }
}
