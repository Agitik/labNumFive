public class Person {
    private java.time.ZonedDateTime birthday; //Поле может быть null
    private Double height; //Поле может быть null, Значение поля должно быть больше 0
    private String passportID; //Длина строки не должна быть больше 36, Поле не может быть null
    private Country nationality; //Поле может быть null
}