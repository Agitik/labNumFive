import java.util.Set;

public class CollectionManager {
    static void addObjectFromBufferAfterParse() throws UnrealValueException {
        try {
            if(TicketCollection.tickets.containsKey(Long.parseLong(Buffer.id))){
                throw new UnrealValueException("Такой ключ уже существует!");
            }
            TicketCollection.tickets.put(Long.parseLong(Buffer.id), new Ticket(
                    Buffer.id,
                    Buffer.name,
                    new Coordinates(Buffer.CordX, Buffer.CordY),
                    Buffer.creationDate,
                    Buffer.price,
                    Buffer.refundable,
                    Buffer.type,
                    new Person(
                            Buffer.weight,
                            Buffer.eyeColor,
                            Buffer.hairColor,
                            Buffer.nationality,
                            new Location(Buffer.LocX, Buffer.LocY, Buffer.LocName)
                    )));
            System.out.println("Я добавил элемент в коллекцию");

        }catch (UnrealValueException e){
            System.out.println("Такой ключ уже существует.");
        }
    }
}
