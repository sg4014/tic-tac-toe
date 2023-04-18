package tictactoe;

import java.util.Random;

public class Bot implements IPlayer{
    private Difficulty level;

    public Bot(Difficulty level) {
        this.level = level;
    }

    public Bot() {
        this(Difficulty.EASY);
    }

    @Override
    public int[] getNextMoveCoordinates(Board board) {
        Random random = new Random();

        while (true) {
            int x = random.nextInt(3) + 1;
            int y = random.nextInt(3) + 1;

            int[] coordinates = new int[]{x, y};

            if (board.isCellFilled(coordinates)) {
                continue;
            }

            System.out.printf("Making move level \"%s\"\n", level);
            return coordinates;
        }
    }

    public Difficulty getLevel() {
        return level;
    }
}
