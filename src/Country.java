/**
 * Перечисление стран, граждане которых могут купить билет.
 * @author Дмитрий Толочек P3130
 * @version 1.0 Before Check
 * @see Person
 */
public enum Country {
    USA,
    GERMANY,
    SOUTH_KOREA;

    public static Country setCountry(String c){
        Country country = null;
        switch (c){
            case "USA":
                country = USA;
                break;
            case "GERMANY":
                country = GERMANY;
                break;
            case "SOUTH_KOREA":
                country = SOUTH_KOREA;
                break;
            default:
                System.out.println("Такой страны нет в списке.");
        }
        return country;
    }
}