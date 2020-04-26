/**
 * Класс для описания билета.
 * @author Дмитрий Толочек P3130
 * @version 1.0 Before Check
 * @see Person
 */

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Ticket implements Comparable<Ticket>{
    private Long id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.LocalDate creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private float price; //Значение поля должно быть больше 0
    private Boolean refundable; //Поле может быть null
    private TicketType type; //Поле не может быть null
    private Person person; //Поле может быть null

    /**
     * Базовый конструктор
     */
    public Ticket(){}

    /**
     * Конструктор через консоль
     * @param key - ключ
     * @param tc - создание/редактирование
     */
    public Ticket(Integer key, boolean tc){
        if(tc){
            if (key > 0){this.id = (long) key * 10;}else{this.id = (long) (key*(-100))+1;}
            setNameFromConsole();
            this.coordinates = new Coordinates();
            this.creationDate = LocalDate.now();
            this.type = TicketType.setTicketTypeFromConsole();
        } else {
            if (key > 0){this.id = (long) key * 10;}else{this.id = (long) (key*(-100))+1;}
            this.creationDate = LocalDate.now();
            this.setNameFromConsole();
            this.coordinates = new Coordinates();
            this.setPriceFromConsole();
            this.type = TicketType.setTicketTypeFromConsole();
        }
    }

    /**
     * Конструктор для изменения значения билета
     * @param t - Билет
     */
    public static void setTicketValue(Ticket t){
        t.setNameFromConsole();
        t.coordinates = new Coordinates();
        t.setPriceFromConsole();
        t.setRefundableFromConsole();
        t.type = TicketType.setTicketTypeFromConsole();
        t.person = Person.setPersonFromConsole();

    }

    /**
     * Конструтор для XML
     * @param i - ID
     * @param n - Name
     * @param c - Coordinates
     * @param ct - Creation Date
     * @param pr - Price
     * @param r - Refundable
     * @param t - Type
     * @param p - Person
     */
    public Ticket (String i, String n, Coordinates c, String ct, String pr, String r, String t, Person p){
        try {
            if(Long.parseLong(i) < 0){throw new UnrealValueException("Число не может быть отрицательным!");}
            this.id = Long.parseLong(i);
        } catch (NumberFormatException | UnrealValueException e){
            System.out.println("Произошла ошибка (Ticket -> id)");
        }

        try {
            this.name = n;
            if (this.name.equals("")){throw new UnrealValueException("Это поле не может быть Null");}
        } catch (UnrealValueException e){
            System.out.println("Произошла ошибка (Ticket -> name). Взято базовое название");
            this.name = "Basic";
        }

        this.coordinates = c;

        try {
            String[] b = ct.split(" ", 3);
            this.creationDate = LocalDate.of(Integer.parseInt(b[0]), Integer.parseInt(b[1]), Integer.parseInt(b[2]));
        }catch (DateTimeException e){
            System.out.println("Указана некорректная дата! Выбрана сегодняшняя дата");
            this.creationDate = LocalDate.now();
        }

        try {
            if(Float.parseFloat(pr) < 0){throw new UnrealValueException("Значение цены должно быть больше нуля!");}
            this.price = Float.parseFloat(pr);
        }catch (NumberFormatException | UnrealValueException e){
            System.out.println("Произошла ошибка (Ticket -> id)");
        }

        if(r.equals("null")){
            this.refundable = null;
        } else {
            this.refundable = Boolean.parseBoolean(r);
        }

        this.type = TicketType.setTicketType(t);

        this.person = p;
    }

    /**
     * Конструктор для текстового представления билета
     */
    public void showInfo(){
        System.out.println(" ");
        System.out.println("***TICKET INFO***\n");
        System.out.println("ID: " + this.id);
        System.out.println("Name: " + this.name);
        System.out.println("Coordinates - " + this.coordinates);
        System.out.println("Creation Date: " + this.creationDate);
        System.out.println("Price: " + this.price);
        System.out.println("Refundable: " + this.refundable);
        System.out.println("Type: " + this.type);
        System.out.println("\n***OWNER INFO***");
        try{
            this.person.showInfo();
        } catch (NullPointerException e){
            System.out.println("Person: No Owner!");
        }
        System.out.println(" ");
    }

    /**
     * Метод для задания названия мероприятия с консоли
     */
    public void setNameFromConsole() {
        try {
            System.out.print("Введите имя события билета: ");
            Scanner in = new Scanner(System.in);
            String c = in.nextLine().trim();
            if (c.trim().length() == 0){throw new UnrealValueException("Вы ничего не ввели.");}
            this.name = c;
        } catch (UnrealValueException e){
            e.getMessage();
            System.out.println("Попробуйте еще раз!");
            setNameFromConsole();
        }
    }

    /**
     * Метод для задания названия цены с консоли
     */
    public void setPriceFromConsole(){
        try {
            Scanner in = new Scanner(System.in);
            System.out.print("Введите цену билета (Дробное число): ");
            String c = in.nextLine().trim();
            if(c.trim().length() == 0){throw new UnrealValueException("Вы ничего не ввели!");}
            if(Float.parseFloat(c) > 0){this.price = Float.parseFloat(c);}else{throw new UnrealValueException("Значение должно быть больше нуля!");}
        } catch (UnrealValueException | NumberFormatException e){
            e.getMessage();
            System.out.println("Введено некорректное значение. Попробуйте еще раз!");
            setPriceFromConsole();
        }
    }

    /**
     * Метод для задания refundable с консоли
     */
    public void setRefundableFromConsole() {
        Scanner in = new Scanner(System.in);
        System.out.print("Возможно ли вернуть билет? (true or false): ");
        String c = in.nextLine().trim();
        if (c.trim().length() == 0) {
            this.refundable = null;
        } else {
            try {
                this.refundable = Boolean.parseBoolean(c);
            } catch (InputMismatchException e){
                System.out.println("Вы ввели неверное значение! Попробуйте еще раз или оставьте строку пустой!");
                setRefundableFromConsole();
            }
        }
    }

    /**
     * Метод для получения ID билета
     * @return - ID билета
     */
    public Long getID(){
        return this.id;
    }

    /**
     * Метод для парсинга билета Java - XML
     * @param filePath - путь к файлу
     */
    public void formatTicketToXml(String filePath){
        ParserWriter.addParseStrToFile(filePath,ParserWriter.addStartElement("ticket", 2));
        ParserWriter.addParseStrToFile(filePath,ParserWriter.addElementWithValue("id", this.id.toString(), 3));
        ParserWriter.addParseStrToFile(filePath,ParserWriter.addElementWithValue("name", this.name.toString(), 3));
        this.coordinates.formatToXML(filePath);
        int y = this.creationDate.getYear();
        int m = this.creationDate.getMonth().getValue();
        int d = this.creationDate.getDayOfMonth();
        String date = Integer.toString(y) + " " + Integer.toString(m) + " " + Integer.toString(d);
        ParserWriter.addParseStrToFile(filePath,ParserWriter.addElementWithValue("creationDate", date, 3));
        ParserWriter.addParseStrToFile(filePath,ParserWriter.addElementWithValue("price", Float.toString(price), 3));
        if (this.refundable == null){
            ParserWriter.addParseStrToFile(filePath,ParserWriter.addElementWithValue("refundable", "null", 3));
        } else {
            ParserWriter.addParseStrToFile(filePath,ParserWriter.addElementWithValue("refundable", this.refundable.toString(), 2));
        }
        ParserWriter.addParseStrToFile(filePath,ParserWriter.addElementWithValue("type", this.type.toString(), 3));
        if(this.person == null){
            ParserWriter.addParseStrToFile(filePath,ParserWriter.addElementWithValue("personNull","null",3));
            ParserWriter.addParseStrToFile(filePath,ParserWriter.addElementWithValue("locationNull","null",3));
        } else {
            person.formatPersonToXML(filePath);
        }
        ParserWriter.addParseStrToFile(filePath,ParserWriter.addEndElement("ticket",2));
    }

    /**
     * Метод для сравнения значения билета
     * @param o - билет
     * @return - Билет 2 - Билет 1
     */
    @Override
    public int compareTo(Ticket o) {
        try{
            int a = (int) this.price * this.creationDate.getDayOfYear();
            int b = (int) o.price * o.creationDate.getDayOfYear();
            return a-b;
        } catch (NullPointerException e){
            return 0;
        }
    }

    /**
     *  Метод для получения типа билета
     * @return Ticket
     */
    public TicketType getType(){
        return this.type;
    }

    /**
     * Метод для получения объекта владельца билета
     * @return Ticket
     */
    public Person getPerson(){
        return this.person;
    }

}


