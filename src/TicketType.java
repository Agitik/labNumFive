/**
 * Перечисление видов билетов.
 * @author Дмитрий Толочек P3130
 * @version 1.0 Before Check
 * @see Person
 */
import java.util.Scanner;

public enum TicketType implements Comparable<TicketType> {
    VIP,
    USUAL,
    BUDGETARY,
    CHEAP;

    /**
     * Устанавливает тип билета, получая строку.
     * @param t - тип билета (Строка)
     * @return - тип билета
     */
    public static TicketType setTicketType(String t){
        TicketType rt = null;
        switch (t.toUpperCase()){
            case "VIP":
                rt = VIP;
                break;
            case "USUAL":
                rt = USUAL;
                break;
            case "BUDGETARY":
                rt = BUDGETARY;
                break;
            case "CHEAP":
                rt = CHEAP;
                break;
            default:
                System.out.println("Такого типа билета не сущетвует!");
        }
        return rt;
    }

    /**
     * Устанавливает тип билета, получая строку с консоли (вводит пользователь)
     * @return тип билета
     */
    public static TicketType setTicketTypeFromConsole(){
        TicketType rt = TicketType.CHEAP;
        try {
            System.out.print("Выберите тип билета (VIP, BUDGETARY, CHEAP, USUAL): ");
            System.out.println("Пишите ровно так, как указано выше или ставьте строку пустой!");
            System.out.print("Введите значение типа билета: ");
            Scanner in = new Scanner(System.in);
            String c = in.nextLine().trim();
            TicketType rtc = setTicketType(c);
            if (rtc == null) {
                throw new UnrealValueException("Вы ввели некорректное значение. Пересмотрите список!");
            } else {rt = rtc;}
        } catch (UnrealValueException e){
            System.out.println("Попробуйте еще раз!");
            System.out.println(e.getMessage());
            setTicketTypeFromConsole();
        }
        return rt;
    }
}