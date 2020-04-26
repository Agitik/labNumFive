import java.util.Scanner;

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

    /**
     * Основная функция задания цвета
     * @param c - цвет
     * @return Color
     */
    public static Color setColor(String c){
        Color color = null;
        switch (c.toUpperCase()){
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

    /**
     * Функция для установки цвета с консоли (Если невозможно Null значение)
     * @return Color
     */
    public static Color setColorFromConsoleImNull(){
        Color rt = Color.RED;
        try {
            System.out.println("Выберите цвет из списка: (RED, BLACK, BLUE, YELLOW, WHITE, GREEN): ");
            System.out.print("Введите значение цвета: ");
            Scanner in = new Scanner(System.in);
            String c = in.nextLine().trim();
            Color rtc = setColor(c);
            if (rtc == null) {
                throw new UnrealValueException("Вы ввели некорректное значение. Пересмотрите список!");
            } else {rt = rtc;}
        } catch (UnrealValueException e){
            System.out.println("Попробуйте еще раз!");
            e.getMessage();
            setColorFromConsoleImNull();
        }
        return rt;
    }

    /**
     * Функция для установки цвета с консоли (Если возможно Null значение)
     * @return Color
     */
    public static Color setColorFromConsolePNull(){
        Color rt = null;
        try {
            System.out.println("Выберите цвет из списка: (RED, BLACK, BLUE, YELLOW, WHITE, GREEN): ");
            System.out.println("Или оставьте строку пустой!");
            System.out.print("Введите значение цвета: ");
            Scanner in = new Scanner(System.in);
            String c = in.nextLine().trim();
            Color color;
            switch (c.trim().toUpperCase()) {
                case "":
                    color = null;
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
                    throw new UnrealValueException("Выберите пункт из списка или оставьте строку пустой!");
            }
            rt = color;
        } catch (UnrealValueException e){
            System.out.println("Попробуйте еще раз!");
            e.getMessage();
            setColorFromConsolePNull();
        }
        return rt;
    }
}