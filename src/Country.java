import java.util.Scanner;

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

    /**
     * Основная функция задания страны
     * @param c - страна
     * @return Country
     */
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

    /**
     * Функция для установки страны с консоли
     * @return Country
     */
    public static Country setCountryFromConsole(){
        Country country = null;
        try {
            System.out.print("Выберите название страны из списка (USA, GERMANY, SOUTH_KOREA) ");
            System.out.println("Пишите ровно так, как указано выше или ставьте строку пустой!");
            System.out.print("Введите название страны: ");
            Scanner in = new Scanner(System.in);
            String c = in.nextLine().trim();
            switch (c.trim().toUpperCase()){
                case "":
                    country = null;
                    break;
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
                    throw new UnrealValueException("Такого в списке нет. Введите значение из списка или оставьте поле пустым!");
            }

        } catch (UnrealValueException e){
            System.out.println("Попробуйте еще раз!");
            System.out.println(e.getMessage());
            setCountryFromConsole();
        }
        return country;
    }
}