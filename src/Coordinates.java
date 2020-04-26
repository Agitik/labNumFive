import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Класс для хранения координат мероприятия: <b>x, y</b>.
 * @author Дмитрий Толочек P3130
 * @version 1.0 Before Check
 * @see Ticket
 */
public class Coordinates {
    private int x;
    private int y;

    /**
     * Конструктор, работабщий через ввод с консоли
     */
    public Coordinates(){
        setXFromConsole();
        setYFromConsole();
    }

    /**
     * Конструктор для ParserReader
     * @param x - х координата
     * @param y - у координата
     */
    public Coordinates(String x, String y){
        try {
            this.x = Integer.parseInt(x);
        } catch (NumberFormatException e){
            System.out.println("Введено некорректное число! (Ticket -> Coordinates -> x).");
        }

        try {
            this.y = Integer.parseInt(y);
        } catch (NumberFormatException e){
            System.out.println("Введено некорректное число! (Ticket -> Coordinates -> x).");
        }
    }

    /**
     * Строковое представление обекта
     * @return Строковое представление объекта
     */
    @Override
    public String toString(){
        return "X: " + this.x + "; Y: " + this.y;
    }

    /**
     * Метод реализацющий установку X координаты с консоли
     */
    public void setXFromConsole(){
        try {
            System.out.print("Введите координату X места проведения мероприятия (Целое число): ");
            Scanner in = new Scanner(System.in);
            String c = in.nextLine().trim();
            if (c.length() == 0){throw new UnrealValueException("Вы ничего не ввели.");}
            this.x = Integer.parseInt(c.trim());
        } catch (NumberFormatException | UnrealValueException e){
            System.out.println("Введено некорректное число или вы ничего не ввели, попробуйте еще раз!");
            e.getMessage();
            setXFromConsole();
        }
    }

    /**
     * Метод реализацющий установку Y координаты с консоли
     */
        public void setYFromConsole() {
            try {
                System.out.print("Введите координату Y места проведения мероприятия (Целое число): ");
                Scanner in = new Scanner(System.in);
                String c = in.nextLine().trim();
                if (c.length() == 0) {
                    throw new UnrealValueException("Вы ничего не ввели.");
                }
                this.y = Integer.parseInt(c.trim());
            } catch (NumberFormatException | UnrealValueException e) {
                System.out.println("Введено некорректное число или вы ничего не ввели, попробуйте еще раз!");
                e.getMessage();
                setYFromConsole();
            }
        }

    /**
     * Метод, реализующий парсинг объекта в XML
     * @param file - ссылка на файл
     */
    public void formatToXML(String file){
            Integer x = this.x;
            Integer y = this.y;
            ParserWriter.addParseStrToFile(file,ParserWriter.addElementWithValue("cordX", x.toString(), 1));
            ParserWriter.addParseStrToFile(file,ParserWriter.addElementWithValue("cordY", y.toString(), 1));
        }

}