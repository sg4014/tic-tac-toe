package tictactoe;

public class Game {
    private final Board board = new Board();
    private final Player playerX;
    private final Player playerO;
    private Player nextMovingPlayer;
    private GameState state = GameState.NOT_FINISHED;

    public Game(Player playerX, Player playerO) {
        this.playerX = playerX;
        this.playerO = playerO;
        this.nextMovingPlayer = playerX;
    }

    /**
     * starts the game
     */
    public void start() {
        runGameLoop();

        /* when game is finished */
        board.print();
        System.out.println(state);
    }

    private void runGameLoop() {
        while (state == GameState.NOT_FINISHED) {
            board.print();
            nextMovingPlayer.makeNextMove(board);
            updateState();
            updateNextMovingPlayer();
        }
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
        if (nextMovingPlayer.equals(playerX)) {
            nextMovingPlayer = playerO;
        } else {
            nextMovingPlayer = playerX;
        }
    }
}
