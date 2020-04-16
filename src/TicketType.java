/**
 * Перечисление видов билетов.
 * @author Дмитрий Толочек P3130
 * @version 1.0 Before Check
 * @see Person
 */
public enum TicketType {
    VIP,
    USUAL,
    BUDGETARY,
    CHEAP;

    public static TicketType setTicketType(String t){
        TicketType rt = null;
        switch (t){
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
        }
        return rt;
    }
}