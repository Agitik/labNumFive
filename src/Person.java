/**
 * Класс для описания покупателя билета.
 * @author Дмитрий Толочек P3130
 * @version 1.0 Before Check
 */
import java.util.InputMismatchException;
import java.util.Scanner;

public class Person implements Comparable<Person> {
    private Double weight; //Поле может быть null, Значение поля должно быть больше 0
    private Color eyeColor; //Поле не может быть null
    private Color hairColor; //Поле может быть null
    private Country nationality; //Поле может быть null
    private Location location; //Поле может быть null

    /**
     * Конструктор через консоль
     */
    public Person(){
        System.out.println("Присутпаем к заполению данных о владельце билета!");
        setWeightFromConsole();
        System.out.println("Выбираем цвет глаз!");
        this.eyeColor = Color.setColorFromConsoleImNull();
        System.out.println("Выбираем цвет волос!");
        this.hairColor = Color.setColorFromConsolePNull();
        this.nationality = Country.setCountryFromConsole();
        this.location = Location.setLocationFromConsole();
    }

    /**
     * Метод для задания покупателя (В взм от выбора пользователя)
     * @return объект Person
     */
    public static Person setPersonFromConsole(){
        Person ret = null;
        boolean qestion;
        Scanner in = new Scanner(System.in);
        System.out.print("Вы хотите заполнить данные владельца билета? (true/false): ");
        try {
            qestion = in.nextBoolean();
            if(qestion){
                ret = new Person();
            }
        }catch (InputMismatchException e){
            System.out.println("Вы ввели неверный ответ на вопрос! Попробуйте еще раз. См. варианты ответа");
            setPersonFromConsole();
        }
        return ret;
    }

    /**
     * Конструктор, берущий данный из XML документа
     * @param w - вес
     * @param e цвет глаз
     * @param h - цвет волос
     * @param n - национальность
     * @param l - место рождения
     */
    public Person(String w, String e, String h, String n, Location l){
        if(w.equals("null")){
            this.weight = null;
        } else {
            try {
                this.weight = Double.parseDouble(w);
                if (weight < 0){
                    throw new UnrealValueException("Введено отрицательное значение веса!");
                }
            } catch (NumberFormatException | UnrealValueException er){
                System.out.println("Некорректное число! (Person -> weight).");
                this.weight = null;
            }
        }

        this.eyeColor = Color.setColor(e);
        if(eyeColor == null){this.eyeColor = Color.setColor("BLUE");}

        if(h.equals("null")){
            this.hairColor = null;
        } else {
            this.hairColor = Color.setColor(h);
        }

        if(n.equals("null")){
            this.nationality = null;
        } else {
            this.nationality = Country.setCountry(n);
        }

        if(l == null){
            this.location = null;
        } else {
            this.location = l;
        }
    }

    /**
     * Метол для показа информации о человеке
     */
    public void showInfo(){
        System.out.println("Weight: " + this.weight);
        System.out.println("EyeColor: " + this.eyeColor);
        System.out.println("HairColor: " + this.hairColor);
        System.out.println("Nationality: " + this.nationality);
        System.out.println("Location - " + this.location);
    }

    /**
     * Метод для задания веса человека с консоли
     */
    public void setWeightFromConsole(){
        try {
            Scanner in = new Scanner(System.in);
            System.out.print("Введите значение веса (Дробное число > 0 или пустая строка): ");
            String c = in.nextLine();
            if(c.trim().length() == 0){this.weight = null;}
            else{
                if(Double.parseDouble(c) > 0){
                    this.weight = Double.parseDouble(c);
                }else{
                    throw new UnrealValueException("Значение должно быть больше нуля!");}
                }
        } catch (UnrealValueException | NumberFormatException e){
            System.out.println("Введено некорректное значение. Попробуйте еще раз!");
            e.getMessage();
            setWeightFromConsole();
        }
    }

    /**
     * Метод для парсинга информации о личности в XML
     * @param file - ссылка на файл
     */
    public void formatPersonToXML(String file){
        ParserWriter.addParseStrToFile(file, ParserWriter.addStartElement("Person",3));
        if(this.weight == null){
            ParserWriter.addParseStrToFile(file,ParserWriter.addElementWithValue("weight","null",4));
        } else {
            ParserWriter.addParseStrToFile(file,ParserWriter.addElementWithValue("weight",this.weight.toString(),4));
        }
        ParserWriter.addParseStrToFile(file,ParserWriter.addElementWithValue("eyeColor",this.eyeColor.toString(),4));
        if(hairColor == null){
            ParserWriter.addParseStrToFile(file,ParserWriter.addElementWithValue("hairColor","null",4));
        } else {
            ParserWriter.addParseStrToFile(file,ParserWriter.addElementWithValue("hairColor",this.hairColor.toString(),4));
        }
        if(nationality == null){
            ParserWriter.addParseStrToFile(file,ParserWriter.addElementWithValue("nationality","null",4));
        } else {
            ParserWriter.addParseStrToFile(file,ParserWriter.addElementWithValue("nationality",this.nationality.toString(),4));
        }
        if(this.location == null){
            ParserWriter.addParseStrToFile(file,ParserWriter.addElementWithValue("locationNull","null",4));
        } else {
            this.location.formatToXML(file);
        }
        ParserWriter.addParseStrToFile(file,ParserWriter.addEndElement("Person",3));
    }

    /**
     * Метод для сравнения объектов Person через вес
     * @param a - Person
     * @return - Person 2 - Person 1
     */
    @Override
    public int compareTo(Person a) {
        Double A;
        Double B;
        if (this.weight == null){A = 0.0;}else{A = this.weight;}
        if (a.weight == null){B = 0.0;}else{B = a.weight;}
        return (int)(A - B);
    }
}