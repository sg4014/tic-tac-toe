package tictactoe;

public class Game {
    private final Board board = new Board();
    private final IPlayer playerX;
    private final IPlayer playerO;
    private IPlayer nextMovingPlayer;
    private GameState state;

    public Game(IPlayer playerX, IPlayer playerO) {
        this.playerX = playerX;
        this.playerO = playerO;
        this.nextMovingPlayer = playerX;
        this.state = GameState.NOT_FINISHED;
    }

    public Game() {
        this(new User(), new Bot());
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

            int[] nextMoveCoordinates = nextMovingPlayer.getNextMoveCoordinates(board);

            board.fillCell(nextMoveCoordinates);

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
