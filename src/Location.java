import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Класс для хранения фактического адреса проживания человека по его:
 * Координатам: <b>x, y</b>
 * И названию пункта проживания: <b>name</b>
 * @author Дмитрий Толочек P3130
 * @version 1.0 Before Check
 * @see Person
 */
public class Location {

    private Long x; //Поле не может быть null
    private float y; //Поле не может быть null
    private String name; //Поле не может быть null

    /**
     * Конструктор для создания объекта через консоль
     */
    public Location(){
        setXFromConsole();
        setYFromConsole();
        setNameFromConsole();
    }

    /**
     * Конструктор для создания объекта через XML
     * @param x - х координата
     * @param y - y координата
     * @param name - место рождения
     */
    public Location(String x, String y, String name){

        this.name = "Basic";
        this.x = 0L;
        this.y = 0.0f;

        try {
            this.x = Long.parseLong(x);
        } catch (NumberFormatException e){
            System.out.println("Введено некорректное число! (Person -> Location -> x) Взято 0 по дефолту.");
        }
        try {
            this.y = Float.parseFloat(y);
        } catch (NumberFormatException e){
            System.out.println("Введено некорректное число! (Person -> Location -> x) Взято 0.0 по дефолту.");
        }
    }

    /**
     * Строковое представление объекта
     */
    @Override
    public String toString(){
        return "Name: " + this.name + ". X: " + this.x + "; Y: " + this.y;
    }

    /**
     * Метод для задания локации с консоли
     * @return - location
     */
    public static Location setLocationFromConsole(){
        Location ret = null;
        boolean qestion;
        Scanner in = new Scanner(System.in);
        System.out.print("Вы хотите ввести значение места рождения? (true/false): ");
        try {
            qestion = in.nextBoolean();
            if(qestion){
                ret = new Location();
            }
        }catch (InputMismatchException e){
            System.out.println("Вы ввели неверный ответ на вопрос! Попробуйте еще раз. См. варианты ответа");
            setLocationFromConsole();
        }
        return ret;
    }

    /**
     * Метод для задания x координаты с консоли
     */
    public void setXFromConsole(){
        try {
            System.out.print("Введите координату X места рождения (Целое число): ");
            Scanner in = new Scanner(System.in);
            String c = in.nextLine();
            if (c.trim().length() == 0){throw new UnrealValueException("Вы ничего не ввели.");}
            this.x = Long.parseLong(c.trim());
        } catch (NumberFormatException | UnrealValueException e){
            System.out.println("Введено некорректное число или вы ничего не ввели, попробуйте еще раз!");
            e.getMessage();
            setXFromConsole();
        }
    }

    /**
     * Метод для задания y координаты с консоли
     */
    public void setYFromConsole(){
        try {
            System.out.print("Введите координату Y места рождения (Дробное число): ");
            Scanner in = new Scanner(System.in);
            String c = in.nextLine();
            if (c.trim().length() == 0){throw new UnrealValueException("Вы ничего не ввели.");}
            this.y = Float.parseFloat(c.trim());
        } catch (NumberFormatException | UnrealValueException e){
            System.out.println("Введено некорректное число или вы ничего не ввели, попробуйте еще раз!");
            e.getMessage();
            setYFromConsole();
        }
    }

    /**
     * Метод для задания name с консоли
     */
    public void setNameFromConsole() {
        try {
            System.out.print("Введите название места рождения: ");
            Scanner in = new Scanner(System.in);
            String c = in.nextLine();
            if (c.trim().length() == 0){throw new UnrealValueException("Вы ничего не ввели.");}
            this.name = c;
        } catch (UnrealValueException e){
            System.out.println("Попробуйте еще раз!");
            e.getMessage();
            setNameFromConsole();
        }
    }

    /**
     * Метод для парсинга Java - XML
     * @param file - ссылка на файл
     */
    public void formatToXML(String file){
        ParserWriter.addParseStrToFile(file,ParserWriter.addElementWithValue("locX", this.x.toString(), 3));
        ParserWriter.addParseStrToFile(file,ParserWriter.addElementWithValue("locY", Double.toString(this.y), 3));
        ParserWriter.addParseStrToFile(file,ParserWriter.addElementWithValue("locName", this.name, 3));
    }
}