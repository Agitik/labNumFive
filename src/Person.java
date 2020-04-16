/**
 * Класс для описания покупателя билета.
 * @author Дмитрий Толочек P3130
 * @version 1.0 Before Check
 */
public class Person {
    private Double weight; //Поле может быть null, Значение поля должно быть больше 0
    private Color eyeColor; //Поле не может быть null
    private Color hairColor; //Поле может быть null
    private Country nationality; //Поле может быть null
    private Location location; //Поле может быть null

    public Person(String w, String e, String h, String n, Location l){
        try {
            this.weight = Double.parseDouble(w);
            if (weight < 0){
                throw new UnrealValueException("Введено отрицательное значение веса!");
            }
        } catch (NumberFormatException | UnrealValueException er){
            System.out.println("Некорректное число! (Person -> weight).");
            this.weight = null;
        }

        this.eyeColor = Color.setColor(e);
        if(eyeColor == null){this.eyeColor = Color.setColor("BLUE");}

        this.hairColor = Color.setColor(h);

        this.nationality = Country.setCountry(n);

        this.location = l;
    }
}