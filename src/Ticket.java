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
}


