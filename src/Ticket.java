import java.time.DateTimeException;
import java.time.LocalDate;

/**
 * Класс для описания билета.
 * @author Дмитрий Толочек P3130
 * @version 1.0 Before Check
 * @see Person
 */
public class Ticket {
    private Long id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.LocalDate creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private float price; //Значение поля должно быть больше 0
    private Boolean refundable; //Поле может быть null
    private TicketType type; //Поле не может быть null
    private Person person; //Поле может быть null

    public Ticket (String i, String n, Coordinates c, String ct, String pr, String r, String t, Person p){
        try {
            if(Long.parseLong(i) < 0){throw new UnrealValueException("Число не может быть отрицательным!");}
            this.id = Long.parseLong(i);
        } catch (NumberFormatException | UnrealValueException e){
            System.out.println("Произошла ошибка (Ticket -> id)");
        }

        try {
            this.name = n;
            if (this.name == ""){throw new UnrealValueException("Это поле не может быть Null");}
            if (this.name == null){throw new UnrealValueException("Это поле не может быть Null");}
        } catch (UnrealValueException e){
            System.out.println("Произошла ошибка (Ticket -> name). Взято базовое название");
            this.name = "Basic";
        }

        this.coordinates = c;

        try {
            String[] b = ct.split(" ", 3);
            this.creationDate = LocalDate.of(Integer.parseInt(b[2]), Integer.parseInt(b[1]), Integer.parseInt(b[0]));
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

        this.refundable = Boolean.parseBoolean(r);

        try {
            if(t.equals(null)){throw new UnrealValueException("Не может быть Null (Ticket type)");}
            this.type = TicketType.setTicketType(t);
        } catch (UnrealValueException e){
            System.out.println("Произошла ошибка при создании типа билета. Взято дефолтное");
            this.type = TicketType.BUDGETARY;
        }

        this.person = p;
    }

}


