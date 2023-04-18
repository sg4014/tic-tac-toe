package tictactoe;

import java.util.InputMismatchException;
import java.util.Scanner;

public class User extends Player {

    public User(CellValue moveSymbol) {
        super(moveSymbol);
    }

    /**
     * Asks the user for their next move
     * until the user provides a valid move,
     * then makes the move (fills the chosen cell on the board).
     * @param board the board on which the move is made
     */
    @Override
    public void makeNextMove(Board board) {
        Scanner scanner = new Scanner(System.in);
        int row;
        int column;

        while (true) {
            System.out.print("Enter the coordinates: ");

            try {
                row = scanner.nextInt();
                column = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("You should enter numbers!");
                scanner.nextLine(); // Don't remove: the loop will become infinite
                // because InputMismatchException doesn't skip the token
                // that caused the exception.
                continue;
            }

            boolean isCoordinatesOutOfRange = (row < 1) || (row > 3)
                    || (column < 1) || (column > 3);

            if (isCoordinatesOutOfRange) {
                System.out.println("Coordinates should be from 1 to 3!");
                continue;
            }

            if (board.isCellFilled(row, column)) {
                System.out.println("The cell is already filled.");
                continue;
            }

            board.fillCellWith(row, column, moveSymbol);
            break;
        }
    };
}
