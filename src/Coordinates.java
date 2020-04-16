/**
 * Класс для хранения координат мероприятия: <b>x, y</b>.
 * @author Дмитрий Толочек P3130
 * @version 1.0 Before Check
 * @see Ticket
 */
public class Coordinates {
    private int x;
    private int y;

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
}