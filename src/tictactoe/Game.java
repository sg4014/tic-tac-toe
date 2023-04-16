package tictactoe;

public class Game {
    private final Board board = new Board();
    private final IPlayer player1 = new User();
    private final IPlayer player2 = new Bot();
    private IPlayer nextMovingPlayer = player1;
    private GameState state = GameState.NOT_FINISHED;

    /**
     * starts the game
     */
    public void start() {

        /* Main game loop */
        while (state == GameState.NOT_FINISHED) {
            board.print();

            int[] nextMoveCoordinates = nextMovingPlayer.getNextMoveCoordinates();

            while (board.isCellFilled(nextMoveCoordinates)) {
                if (nextMovingPlayer instanceof User) {
                    System.out.println("The cell is already filled.");
                }

                nextMoveCoordinates = nextMovingPlayer.getNextMoveCoordinates();
            }

            if (nextMovingPlayer instanceof Bot) {
                System.out.println("Making move level " + "\""
                        + ((Bot) nextMovingPlayer).getLevel() + "\"");
            }

            board.fillCell(nextMoveCoordinates);

            updateState();
            updateNextMovingPlayer();
        }

        board.print();
        System.out.println(state);
    }

    private void updateState() {
        if (board.isThreeInRow(CellValue.X)) {
            state = GameState.X_WINS;
        } else if (board.isThreeInRow(CellValue.O)) {
            state = GameState.O_WINS;
        } else if (board.isFull()) {
            state = GameState.DRAW;
        } else {
            state = GameState.NOT_FINISHED;
        }
    }

    private void updateNextMovingPlayer() {
        if (nextMovingPlayer.equals(player1)) {
            nextMovingPlayer = player2;
        } else {
            nextMovingPlayer = player1;
        }
    }
}
