package tictactoe;

import java.util.InputMismatchException;
import java.util.Scanner;

public class User extends Player {

    public User(CellValue moveSymbol) {
        super(moveSymbol);
    }
    @Override
    public void makeNextMove(Board board) {
        Scanner scanner = new Scanner(System.in);
        int x;
        int y;

        while (true) {
            System.out.print("Enter the coordinates: ");

            try {
                x = scanner.nextInt();
                y = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("You should enter numbers!");
                scanner.nextLine(); // Don't remove: the loop will become infinite
                // because InputMismatchException doesn't skip the token
                // that caused the exception.
                continue;
            }

            boolean isCoordinatesOutOfRange = (x < 1) || (x > 3)
                    || (y < 1) || (y > 3);

            if (isCoordinatesOutOfRange) {
                System.out.println("Coordinates should be from 1 to 3!");
                continue;
            }

            if (board.isCellFilled(x, y)) {
                System.out.println("The cell is already filled.");
                continue;
            }

            board.fillCell(x, y);
            break;
        }
    };
}
