/**
 * Класс, содержащий основные методы доступа к коллекции
 * Дмитрий Толочек P3130
 * @version 1.0 Before Check
 * @see TicketCollection
 */

import java.util.Set;

public class CollectionManager {
    /**
     * Функция для добавления объектов в коллекцию из буфера
     */
    static void addObjectFromBufferAfterParse() throws UnrealValueException {
        Person per;
        Location l;
        if (Buffer.location.equals("null")){
            l = null;
        } else {
            l = new Location(Buffer.LocX, Buffer.LocY, Buffer.LocName);
        }
        if(Buffer.person.equals("null")){
            per = null;
        } else {
            per = new Person(
                    Buffer.weight,
                    Buffer.eyeColor,
                    Buffer.hairColor,
                    Buffer.nationality,
                    l
            );
        }

        TicketCollection.tickets.put(TicketCollection.tickets.size() + 1, new Ticket(
                Buffer.id,
                Buffer.name,
                new Coordinates(Buffer.CordX, Buffer.CordY),
                Buffer.creationDate,
                Buffer.price,
                Buffer.refundable,
                Buffer.type,
                per));
            System.out.println("Добавлен элемент коллекции!");
    }
}
