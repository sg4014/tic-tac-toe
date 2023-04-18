package tictactoe;

public class Game {
    private final Board board = new Board();
    private IPlayer playerX;
    private IPlayer playerO;
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
        if (nextMovingPlayer.equals(playerX)) {
            nextMovingPlayer = playerO;
        } else {
            nextMovingPlayer = playerX;
        }
    }
}
