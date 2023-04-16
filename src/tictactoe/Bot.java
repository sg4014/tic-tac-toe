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
    public int[] getNextMoveCoordinates() {
        Random random = new Random();

        int x = random.nextInt(3) + 1;
        int y = random.nextInt(3) + 1;

        return new int[]{x, y};
    }

    public Difficulty getLevel() {
        return level;
    }
}
