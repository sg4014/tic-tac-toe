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

            IPlayer playerX = getPlayer(inputArguments[1]);
            IPlayer playerO = getPlayer(inputArguments[2]);

            Game game = new Game(playerX, playerO);
            game.start();
        }
    }

    private static IPlayer getPlayer(String whoPlays) {
        return switch (whoPlays) {
            case "easy" -> new Bot();
            case "user" -> new User();
            default -> throw new IllegalArgumentException("Unknown type of player: "
                    + whoPlays);
        };
    }
}
