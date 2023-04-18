package tictactoe;

import java.util.Random;

public class EasyBot extends Player {

    public EasyBot(CellValue moveSymbol) {
        super(moveSymbol);
    }

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
            board.fillCell(x, y);
            break;
        }
    }
}
