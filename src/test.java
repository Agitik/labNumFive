import javax.xml.stream.XMLStreamException;
import java.io.IOException;

public class test {
    public static void main(String[] args) throws IOException, XMLStreamException {
        Parser.Read("D:\\Java Projects\\labNumFive\\Testing\\src\\input.xml");
        System.out.println("Я все!");
        System.out.println(TicketCollection.tickets.size());
    }
}
