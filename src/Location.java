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
    private float y;
    private String name; //Поле не может быть null

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
        this.name = name;
    }
}