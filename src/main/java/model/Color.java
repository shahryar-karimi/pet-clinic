package model;

public enum Color {
    BLACK,
    WHITE,
    RED,
    ORANGE,
    YELLOW,
    GREEN,
    BLUE,
    PURPLE,
    BROWN,
    GRAY;

    public static Color getColor(int id) {
        return switch (id) {
            case 1 -> BLACK;
            case 2 -> WHITE;
            case 3 -> RED;
            case 4 -> ORANGE;
            case 5 -> YELLOW;
            case 6 -> GREEN;
            case 7 -> BLUE;
            case 8 -> PURPLE;
            case 9 -> BROWN;
            case 10 -> GRAY;
            default -> null;
        };
    }
}
