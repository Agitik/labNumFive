/**
 * Класс для парсинка XML -> Java
 * @author Дмитрий Толочек P3130
 * @version 1.0 Before Check
 */

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.*;

public class ParserReader {

    public static String fName = "";

    /**
     * Метод, реализующий сам парсинг.
     * @param f - Ссылка на файл
     * @throws IOException - ошибка при чтении
     * @throws XMLStreamException - ошибка с XML
     */
    public static void Read(String f) throws IOException, XMLStreamException {
        try {
            boolean id, name, cordX, cordY, creationDate, price, refundable, type, weight, eyeColor, hairColor, nationality,
                    locX, locY, locName, initDate;
            id = name = cordX = cordY = creationDate = price = refundable = type = weight = eyeColor = hairColor = nationality = locX = locY = locName = false;
            initDate = false;
            Boolean personNull, locationNull, endTicket, makeingDay;
            personNull = locationNull = makeingDay = endTicket = false;
            Boolean isObjectReal = false;

            FileInputStream file = new FileInputStream(f);
            InputStreamReader fileReader = new InputStreamReader(file);
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLEventReader eventReader = factory.createXMLEventReader(fileReader);

            while (eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();
                switch (event.getEventType()) {
                    case XMLStreamConstants.START_ELEMENT:
                        StartElement startElement = event.asStartElement();
                        String qName = startElement.getName().getLocalPart();
                        if (qName.equalsIgnoreCase("id")) {
                            id = true;
                            isObjectReal = true;
                        } else if(qName.equalsIgnoreCase("makingDay")){
                            makeingDay = true;
                        } else if (qName.equalsIgnoreCase("name")) {
                            name = true;
                        } else if (qName.equalsIgnoreCase("cordX")) {
                            cordX = true;
                        } else if (qName.equalsIgnoreCase("cordY")) {
                            cordY = true;
                        } else if (qName.equalsIgnoreCase("creationDate")) {
                            creationDate = true;
                        } else if (qName.equalsIgnoreCase("price")) {
                            price = true;
                        } else if (qName.equalsIgnoreCase("refundable")) {
                            refundable = true;
                        } else if (qName.equalsIgnoreCase("type")) {
                            type = true;
                        } else if (qName.equalsIgnoreCase("personNull")){
                            personNull = true;
                        } else if (qName.equalsIgnoreCase("weight")) {
                            weight = true;
                        } else if (qName.equalsIgnoreCase("eyeColor")) {
                            eyeColor = true;
                        } else if (qName.equalsIgnoreCase("hairColor")) {
                            hairColor = true;
                        } else if (qName.equalsIgnoreCase("nationality")) {
                            nationality = true;
                        } else if (qName.equalsIgnoreCase("locX")) {
                            locX = true;
                        } else if (qName.equalsIgnoreCase("locY")) {
                            locY = true;
                        } else if (qName.equalsIgnoreCase("locName")) {
                            locName = true;
                        } else if (qName.equalsIgnoreCase("initDate")){
                            initDate = true;
                        } else if (qName.equalsIgnoreCase("locationNull")){
                            locationNull = true;
                        }
                        break;
                    case XMLStreamConstants.CHARACTERS:
                        Characters characters = event.asCharacters();
                        if (id) {
                            Buffer.id = characters.getData();
                            id = false;
                        } else if (name) {
                            Buffer.name = characters.getData();
                            name = false;
                        } else if (cordX) {
                            Buffer.CordX = characters.getData();
                            cordX = false;
                        } else if (cordY) {
                            Buffer.CordY = characters.getData();
                            cordY = false;
                        } else if (creationDate) {
                            Buffer.creationDate = characters.getData();
                            creationDate = false;
                        } else if (price) {
                            Buffer.price = characters.getData();
                            price = false;
                        } else if (refundable) {
                            Buffer.refundable = characters.getData();
                            refundable = false;
                        } else if (type) {
                            Buffer.type = characters.getData();
                            type = false;
                        } else if (weight) {
                            Buffer.weight = characters.getData();
                            weight = false;
                        } else if (personNull) {
                            if(characters.getData().equals("null")) {
                                Buffer.person = "null";
                            }
                            personNull = false;
                        } else if (eyeColor) {
                            Buffer.eyeColor = characters.getData();
                            eyeColor = false;
                        } else if (hairColor) {
                            Buffer.hairColor = characters.getData();
                            hairColor = false;
                        } else if (nationality) {
                            Buffer.nationality = characters.getData();
                            nationality = false;
                        } else if(locationNull){
                            if(characters.getData().equals("null")) {
                                Buffer.location = "null";
                                locationNull = false;
                            }
                        } else if (locX) {
                            Buffer.LocX = characters.getData();
                            locX = false;
                        } else if (locY) {
                            Buffer.LocY = characters.getData();
                            locY = false;
                        } else if (locName) {
                            Buffer.LocName = characters.getData();
                            locName = false;
                        } else if (initDate){
                            Buffer.initDate = characters.getData();
                            initDate = false;
                        } else if (makeingDay){
                            Buffer.makingDay = characters.getData();
                            makeingDay = false;
                        }
                        break;
                case XMLStreamConstants.END_ELEMENT:
                    EndElement endElement = event.asEndElement();
                    String eName = endElement.getName().getLocalPart();
                    if(eName.equalsIgnoreCase("ticket")){
                        if(isObjectReal){
                            CollectionManager.addObjectFromBufferAfterParse();
                            Buffer.BufferClear();
                        } else {
                            System.out.println("В файле отсутствуют элементы коллекции!");
                        }
                    }
                }
            }
        } catch (IOException e){
            System.out.println("Ошибка при чтении файла!");
        } catch (XMLStreamException e){
            System.out.println("Ошибка при чтении XML документа!");
        } catch (UnrealValueException e) {
            System.out.println("Обнаружены объекты с одинаковым значением id!");
        }
    }
}
