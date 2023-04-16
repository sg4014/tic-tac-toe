package tictactoe;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    private static final String[][] table = new String[3][3];
    private static String nextMoveSymbol;
    private static GameState gameState = GameState.NOT_FINISHED;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the cells: ");
        String input = scanner.nextLine();
        assertLengthIsValid(input);
        assertAllSymbolsAreValid(input);
        setInitialTable(input);
        setInitialMoveSymbol();
        printTable();

        nextMove();
        updateNextMoveSymbol();
        updateGameState();
        printTable();
        System.out.println(gameState);
    }

    private static void assertLengthIsValid(String initialTable) {
        if (initialTable.length() != 9) {
            throw new IllegalArgumentException("The initial table "
                    + "must contain exactly 9 symbols.");
        }
    }

    private static void assertAllSymbolsAreValid(String initialTable) {
        Pattern p = Pattern.compile("[^XO_]");
        Matcher m = p.matcher(initialTable);
        boolean isContainsInvalidSymbols = m.find();

        if (isContainsInvalidSymbols) {
            throw new IllegalArgumentException("The initial table "
                    + "must include only X, O, _ symbols.");
        }
    }

    private static void setInitialTable(String initialTable) {
        int k = 0;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                char cellValue = initialTable.charAt(k);

                if (cellValue == '_') {
                    table[i][j] = " ";
                } else {
                    table[i][j] = Character.toString(initialTable.charAt(k));
                }

                k += 1;
            }
        }
    }

    private static void setInitialMoveSymbol() {
        int numberOfX = getNumberOf("X");
        int numberOfO = getNumberOf("O");

        if (numberOfX > numberOfO) {
            nextMoveSymbol = "O";
        } else {
            nextMoveSymbol = "X";
        }
    }

    private static int getNumberOf(String symbol) {
        int count = 0;

        for (String[] row: table) {
            for (String cellValue: row) {
                if (cellValue.equals(symbol)) {
                    count++;
                }
            }
        }

        return count;
    }


    private static void printTable() {
        System.out.println("---------");

        for (String[] row: table) {
            System.out.println("| " + String.join(" ", row) + " |");
        }

        System.out.println("---------");
    }

    private static void updateGameState() {
        if (isThreeInRow("X")) {
            gameState = GameState.X_WINS;
        } else if (isThreeInRow("O")) {
            gameState = GameState.O_WINS;
        } else if (isTableFull()) {
            gameState = GameState.DRAW;
        } else {
            gameState = GameState.NOT_FINISHED;
        }
    }

    private static boolean isThreeInRow(String symbol) {
        return isThreeInHorizontalRow(symbol)
                || isThreeInVerticalRow(symbol)
                || isThreeInDiagonalRow(symbol);
    }

    private static boolean isThreeInHorizontalRow(String symbol) {
        for (String[] horizontalRow: table) {
            if (Arrays.stream(horizontalRow)
                    .allMatch(s -> s.equals(symbol))) {
                return true;
            };
        }

        return false;
    }

    private static boolean isThreeInVerticalRow(String symbol) {
        outer:
        for (int colIndex = 0; colIndex < 3; colIndex++) {

            // iterate over column
            for (int rowIndex = 0; rowIndex < 3; rowIndex++) {
                String tableSymbol = table[rowIndex][colIndex];

                if (!(tableSymbol.equals(symbol))) {
                    continue outer; // check next column
                }
            }

            return true;
        }

        return false;
    }

    private static boolean isThreeInDiagonalRow(String symbol) {
        return isThreeInMainDiagonal(symbol)
                || isThreeInCounterDiagonal(symbol);
    }

    private static boolean isThreeInMainDiagonal(String symbol) {
        for (int i = 0; i < 3; i++) {
            String tableSymbol = table[i][i];

            if (!(tableSymbol.equals(symbol))) {
                return false;
            }
        }

        return true;
    }

    private static boolean isThreeInCounterDiagonal(String symbol) {
        for (int i = 0; i < 3; i++) {
            String tableSymbol = table[2 - i][i];

            if (!(tableSymbol.equals(symbol))) {
                return false;
            }
        }

        return true;
    }

    private static boolean isTableFull() {
        for (String[] row: table) {
            if (Arrays.asList(row).contains(" ")) {
                return false;
            }
        }

        return true;
    }

    private static void nextMove() {
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

            boolean isCellOccupied = table[x - 1][y - 1].matches("[XO]");

            if (isCellOccupied) {
                System.out.println("This cell is occupied! Choose another one!");
                continue;
            }

            table[x - 1][y - 1] = nextMoveSymbol;
            break;
        }
    }

    private static void updateNextMoveSymbol() {
        if (nextMoveSymbol.equals("X")) {
            nextMoveSymbol = "O";
        } else {
            nextMoveSymbol = "X";
        }
    }
}
