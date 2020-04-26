import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    /**
     * main метод для принятия ввода пользователя, main метод программы
     * @param args - ссылка на файл (просто строка)
     */
    public static void main(String[] args) throws IOException, XMLStreamException {
           try{
               Commands.FilePath = args[0];
               ParserReader.Read(Commands.FilePath);
               Buffer.setInitCollectionDate();
               System.out.println("");
               while (Commands.runCode){
                   System.out.print("in> ");
                   Scanner sc = new Scanner(System.in);
                   String str = sc.nextLine();
                   System.out.println("\nВвод принят, обработка...\n");
                   Commands.catchAndExecuteCommand(str);
               }
           } catch (IOException | XMLStreamException e){
               System.out.println("\nПроизошла ошибка при чтении вводимых данных\n");
           } catch (ArrayIndexOutOfBoundsException e){
               System.out.println("\nВы не указали файл!\n");
           }
    }
}
