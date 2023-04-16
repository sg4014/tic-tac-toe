package tictactoe;

public enum Difficulty {
    EASY, MEDIUM, UNBEATABLE;

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
