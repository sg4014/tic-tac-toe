package tictactoe;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        runMenuLoop();
    }

    private static void runMenuLoop() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Input command: ");
            String input = scanner.nextLine();

            if (input.equals("exit")) {
                System.exit(0);
            }

            String[] inputArguments = input.split(" ");

            boolean isBadParameters = inputArguments.length != 3
                    || !(inputArguments[0].equals("start"));

            if (isBadParameters) {
                System.out.println("Bad parameters!");
                continue;
            }

            Player playerX = getPlayer(inputArguments[1], CellValue.X);
            Player playerO = getPlayer(inputArguments[2], CellValue.O);

            Game game = new Game(playerX, playerO);
            game.start();
        }
    }

    private static Player getPlayer(String whoPlays, CellValue moveSymbol) {
        return switch (whoPlays) {
            case "easy" -> new EasyBot(moveSymbol);
            case "user" -> new User(moveSymbol);
            case "medium" -> new MediumBot(moveSymbol);
            default -> throw new IllegalArgumentException("Unknown type of player: "
                    + whoPlays);
        };
    }
}
