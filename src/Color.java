/**
 * Перечесление цветов.
 * @author Дмитрий Толочек P3130
 * @version 1.0 Before Check
 * @see Person
 */
public enum Color {
    RED,
    BLACK,
    BLUE,
    YELLOW,
    WHITE,
    GREEN;

    public static Color setColor(String c){
        Color color = null;
        switch (c){
            case "RED":
                color = RED;
                break;
            case "BLACK":
                color = BLACK;
                break;
            case "BLUE":
                color = BLUE;
                break;
            case "YELLOW":
                color = YELLOW;
                break;
            case "WHITE":
                color = WHITE;
                break;
            case "GREEN":
                color = GREEN;
                break;
            default:
                System.out.println("Такого цвета не существует.");
        }
        return color;
    }
}