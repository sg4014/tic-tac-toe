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

            int[] coordinates = new int[]{x, y};

            if (board.isCellFilled(coordinates)) {
                continue;
            }

            System.out.println("Making move level \"easy\"");
            board.fillCell(coordinates);
            break;
        }
    }
}
