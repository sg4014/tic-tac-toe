package tictactoe;

import java.util.Random;

public class EasyBot extends Player {

    public EasyBot(CellValue moveSymbol) {
        super(moveSymbol);
    }

    /**
     * Makes a random valid move
     * @param board the board on which the move is made
     */
    @Override
    public void makeNextMove(Board board) {
        Random random = new Random();

        while (true) {
            int x = random.nextInt(3) + 1;
            int y = random.nextInt(3) + 1;


            if (board.isCellFilled(x, y)) {
                continue;
            }

            System.out.println("Making move level \"easy\"");
            board.fillCellWith(x, y, moveSymbol);
            break;
        }
    }
}
