/**
 * Класс - буфер между XML и Java представлением билетов
 * @author Дмитрий Толочек P3130
 * @version 1.0 Before Check
 * @see ParserReader
 */

import java.time.DateTimeException;
import java.time.LocalDate;

public class Buffer {
    static LocalDate initCollectionDate;
    static String id;                      //ticket
    static String name;                    //ticket
    static String CordX;                   //coordinates
    static String CordY;                   //coordinates
    static String creationDate;            //ticket
    static String price;                   //ticket
    static String refundable;              //ticket
    static String type;                    //ticket
    static String weight;                  //person
    static String eyeColor;                //person
    static String hairColor;               //person
    static String nationality;             //person
    static String LocX;                    //location
    static String LocY;                    //location
    static String LocName;                 //location
    static String initDate;
    static String person = "";
    static String location = "";
    static String makingDay = "";

    static void BufferClear (){
        Buffer.id = null;                      //ticket
        Buffer.name = null;                    //ticket
        Buffer.CordX = null;                   //coordinates
        Buffer.CordY = null;                   //coordinates
        Buffer.creationDate = null;            //ticket
        Buffer.price = null;                   //ticket
        Buffer.refundable = null;              //ticket
        Buffer.type = null;                    //ticket
        Buffer.weight = null;                  //person
        Buffer.eyeColor = null;                //person
        Buffer.hairColor = null;               //person
        Buffer.nationality = null;             //person
        Buffer.LocX = null;                    //location
        Buffer.LocY = null;                    //location
        Buffer.LocName = null;                 //location
        Buffer.initDate = null;
        Buffer.person = "";
        Buffer.location = "";
    }

    /**
     * Задает время инициализации коллекции
     */
    public static void setInitCollectionDate(){
        if (makingDay.equals("")){
            Buffer.initCollectionDate = LocalDate.now();
        } else {
            try {
                String[] b = Buffer.makingDay.split(" ", 3);
                Buffer.initCollectionDate = LocalDate.of(Integer.parseInt(b[0]), Integer.parseInt(b[1]), Integer.parseInt(b[2]));
            }catch (DateTimeException e){
                Buffer.initCollectionDate = LocalDate.now();
            }
        }
    }
}
