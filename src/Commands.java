/**
 * Класс, риализующий все команды приложения
 * @author Дмитрий Толочек P3130
 * @version 1.0 Before Check
 * @see Main
 */

import java.io.*;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Commands {

    static boolean runCode = true;

    static String FilePath = "";

    /**
     * Реализация команды "Help"
     */
    static void help(){
        System.out.println(" ");
        System.out.println("help : вывести справку по доступным командам");
        System.out.println("info : вывести в стандартный поток вывода информацию о коллекции");
        System.out.println("show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении");
        System.out.println("insert null *ключ* : добавить новый элемент с заданным ключом");
        System.out.println("update id *id* : обновить значение элемента коллекции, id которого равен заданному");
        System.out.println("remove_key null *ключ* : удалить элемент из коллекции по его ключу");
        System.out.println("clear : очистить коллекцию");
        System.out.println("save : сохранить коллекцию в файл");
        System.out.println("execute_script *file_name* : считать и исполнить скрипт из указанного файла.");
        System.out.println("exit : завершить программу (без сохранения в файл)");
        System.out.println("replace_if_greater null *ключ* : заменить значение по ключу, если новое значение больше старого");
        System.out.println("remove_greater_key null *ключ* : удалить из коллекции все элементы, ключ которых превышает заданный");
        System.out.println("remove_lower_key null *ключ* : удалить из коллекции все элементы, ключ которых меньше, чем заданный");
        System.out.println("count_less_than_type *type* : вывести количество элементов, значение поля type которых меньше заданного");
        System.out.println("print_field_ascending_person : вывести значения поля person всех элементов в порядке возрастания");
        System.out.println("print_field_descending_type : вывести значения поля type всех элементов в порядке убывания");
        System.out.println(" ");
    }

    /**
     * Реализация команды "info"
     */
    static void info(){
        String date;
        if(TicketCollection.tickets.size() > 0){
            System.out.println(" ");
            System.out.println("Тип коллекции: HashTable");
            System.out.println("Тип хранимых объектов: Tickets");
            System.out.println("Колличество элементов: " + TicketCollection.tickets.size());
            if(Buffer.initCollectionDate == null){
                Buffer.initCollectionDate = LocalDate.now();
                date = "" + Buffer.initCollectionDate.getYear() + " " + Buffer.initCollectionDate.getMonthValue() + " " + Buffer.initCollectionDate.getDayOfMonth();
            } else {
                date = "" + Buffer.initCollectionDate.getYear() + " " + Buffer.initCollectionDate.getMonthValue() + " " + Buffer.initCollectionDate.getDayOfMonth();
            }
            System.out.println("Дата инициализации коллекции: " + date);
            System.out.println(" ");
        } else {
            System.out.println(" ");
            System.out.println("Тип коллекции: HashTable");
            System.out.println("Тип хранимых объектов: Tickets");
            System.out.println("Колличество элементов: 0");
            if(Buffer.initCollectionDate == null){
                Buffer.initCollectionDate = LocalDate.now();
                date = "" + Buffer.initCollectionDate.getYear() + " " + Buffer.initCollectionDate.getMonthValue() + " " + Buffer.initCollectionDate.getDayOfMonth();
            } else {
                date = "" + Buffer.initCollectionDate.getYear() + " " + Buffer.initCollectionDate.getMonthValue() + " " + Buffer.initCollectionDate.getDayOfMonth();
            }
            System.out.println("Дата инициализации коллекции: " + date);
            System.out.println(" ");
        }
    }

    /**
     * Реализация команды "show"
     */
    static void show(){
        if(TicketCollection.tickets.size() > 0) {
            for (Integer key : TicketCollection.tickets.keySet()) {
                Ticket ticket = TicketCollection.tickets.get(key);
                ticket.showInfo();
            }
        } else {
            System.out.println("В коллекции пока нет объектов!");
        }
    }

    /**
     * Реализация команды "insert null"
     * @param key - ключ нового объекта
     */
    static void insertNull(Integer key){
        System.out.println(" ");
        if(TicketCollection.tickets.containsKey(key)){
            System.out.println("Такой ключ уже есть в коллекции!");
        } else {
            Ticket n = new Ticket(key,true);
            TicketCollection.tickets.put(key,n);
        }
        System.out.println(" ");
    }

    /**
     * Реализация команды "Update id"
     * @param i - ID
     */
    static void updateId (Long i){
        System.out.println(" ");
        Boolean ticketIsReal = false;
        for(Integer key : TicketCollection.tickets.keySet()) {
            Ticket ticket = TicketCollection.tickets.get(key);
            if (ticket.getID().equals(i)){
                Ticket.setTicketValue(ticket);
                ticketIsReal = true;
            }
        }
        if (ticketIsReal.equals(false)){
            System.out.println("Билета с таким ID не существует!");
            System.out.println("Используйте Show для просмотра всех элементов коллекции");
        }
        System.out.println(" ");
    }

    /**
     * Реализация команды "remove key"
     * @param key - ключ объекта
     */
    static void removeKey (Integer key){
        System.out.println(" ");
        if (TicketCollection.tickets.containsKey(key)){
            TicketCollection.tickets.remove(key);
            System.out.println("Билет был удален из базы!");
        } else {
            System.out.println("Билета с таким ID не существует!");
            System.out.println("Используйте Show для просмотра всех элементов коллекции");
        }
        System.out.println(" ");
    }

    /**
     * Реализация команды "Clear Collection"
     */
    static void clearCollection(){
        System.out.println(" ");
        TicketCollection.tickets.clear();
        Buffer.initCollectionDate = LocalDate.now();
        System.out.println("Все билеты в коллекции были удалены!");
        System.out.println(" ");
    }

    /**
     * Реализация команды "save"
     * @param filePath - xml файл, куда сохраняется информация
     */
    static void save(String filePath){
        System.out.println(" ");
        ParserWriter.cleanFile(filePath);
        ParserWriter.addParseStrToFile(filePath,ParserWriter.XMLVesrion());
        String date = "" + Buffer.initCollectionDate.getYear() + " " + Buffer.initCollectionDate.getMonthValue() + " " + Buffer.initCollectionDate.getDayOfMonth();
        ParserWriter.addParseStrToFile(filePath, ParserWriter.addStartElement("tickets",0));
        ParserWriter.addParseStrToFile(filePath,ParserWriter.addElementWithValue("makingDay", date, 0 ));
        for(Integer key : TicketCollection.tickets.keySet()) {
            Ticket ticket = TicketCollection.tickets.get(key);
            ticket.formatTicketToXml(filePath);
        }
        ParserWriter.addParseStrToFile(filePath,ParserWriter.addEndElement("tickets",0));
        System.out.println("Команда выполнена!");
        System.out.println(" ");
    }

    /**
     * Реализация команды "if_greater_than_null"
     * @param key - ключ объекта
     */
    static void if_greater_than_null(Integer key){
        if(TicketCollection.tickets.containsKey(key)){
            System.out.println("");
            System.out.println("Заполните шаблон билета!");
            Ticket newValue = new Ticket(key,false);

            if(newValue.compareTo(TicketCollection.tickets.get(key)) > 0){
                Ticket tik = TicketCollection.tickets.get(key);
                tik = newValue;
                TicketCollection.tickets.remove(key);
                TicketCollection.tickets.put(key,tik);
                System.out.println("Заменено");
            } else {
                System.out.println("Не заменено!");
                System.out.println("");
            }
        } else {
            System.out.println("\nБилета с таким id не существует!\n");
        }
    }

    /**
     * Реализация уоманды "remove_if_grater_than"
     * @param key - ключ объекта
     */
    static void remove_if_greater_key_null(Integer key){
        Set<Integer> set = Collections.newSetFromMap(new ConcurrentHashMap<>());
        for(Integer k : set) {
            if (k > key){
                TicketCollection.tickets.remove(k);
            }
            System.out.println("\nКоманда выполнена!");
        }
    }

    static void remove_if_lover_key_null(Integer key) {
        Set<Integer> set = Collections.newSetFromMap(new ConcurrentHashMap<>());
        for (Integer k : set) {
            if (k < key) {
                TicketCollection.tickets.remove(k);
            }
        }
        System.out.println("\nКоманда выполнена!");
    }

    /**
     * Реализация команды "count_less_than_type"
     * @param type - тип билета, сортировка по системе социализма
     */
    static void count_less_than_type(TicketType type){
        try{
            int count = type.ordinal();
            int sum = 0;
            for (Integer k : TicketCollection.tickets.keySet()) {
                Ticket ticket = TicketCollection.tickets.get(k);
                if(ticket.getType().ordinal() < count){
                    sum++;
                }
            }
            System.out.println("\nКол-во элементов: " + sum);
            System.out.println("\nКоманда выполнена!");
        } catch (NullPointerException e){
            System.out.println("Произошла ошибка!");
        }
    }

    /**
     * Реализация команды "print_field_ascending_person"
     */
    static void print_field_ascending_person(){
        List<Person> personList = new ArrayList<>();
        for (Integer k : TicketCollection.tickets.keySet()) {
            Ticket ticket = TicketCollection.tickets.get(k);
            if(ticket.getPerson() != null){
                personList.add(ticket.getPerson());
            }
        }
        Collections.sort(personList);
        System.out.println("");
        for (Person i : personList){
            i.showInfo();
            System.out.println("");
        }
        if(personList.size() == 0){
            System.out.println("Нет ни одного не null объекта Person!");
        }
    }

    /**
     * Реализация команды "print_field_descending_type"
     */
    static void print_field_descending_type(){
        List<TicketType> typeList = new ArrayList<>();
        for (Integer k : TicketCollection.tickets.keySet()) {
            Ticket ticket = TicketCollection.tickets.get(k);
            typeList.add(ticket.getType());
        }
        Collections.sort(typeList);
        for (TicketType i : typeList){
            System.out.println(i);
        }
    }

    /**
     * Реализация команды "exit"
     */
    static void exit(){
        runCode = false;
        System.out.println("\nРабота программы завершена!");
    }

    /**
     * Данный метод вызывает метод в соостветствии с веденной командой пользователем
     * @param str - строка, которую ввел пользователь
     */
    static void catchAndExecuteCommand(String str){
        String pcom = str.trim();
        if(pcom.toLowerCase().equals("help")){
            Commands.help();
        } else if(pcom.toLowerCase().equals("info")){
            Commands.info();
        } else if (pcom.toLowerCase().equals("show")){
            Commands.show();
        } else if(pcom.toLowerCase().contains("insert null")){
            pcom = pcom.trim();
            pcom = pcom.replace(" ", "");
            pcom = pcom.replace("   ", "");
            pcom = pcom.replace("insertnull","");
            try {
                insertNull(Integer.parseInt(pcom));
            } catch (NumberFormatException e){
                System.out.println("Введено неккоректное значение команды! (ожидался Integer аргумент)");
            }
        } else if(pcom.toLowerCase().contains("update id")){
            pcom = pcom.trim();
            pcom = pcom.replace(" ", "");
            pcom = pcom.replace("   ", "");
            pcom = pcom.replace("updateid","");
            try {
                Commands.updateId(Long.parseLong(pcom));
            } catch (NumberFormatException e){
                System.out.println("Введено неккоректное значение команды! (ожидался Long аргумент)");
            }
        } else if(pcom.toLowerCase().contains("remove_key null")){
            pcom = pcom.trim();
            pcom = pcom.replace(" ", "");
            pcom = pcom.replace("   ", "");
            pcom = pcom.replace("remove_keynull","");
            try {
                removeKey(Integer.parseInt(pcom));
            } catch (NumberFormatException e){
                System.out.println("Введено неккоректное значение команды! (ожидался Integer аргумент)");
            }
        } else if(pcom.toLowerCase().equals("clear")){
            Commands.clearCollection();
        } else if(pcom.toLowerCase().equals("save")){
            Commands.save(Commands.FilePath);
        } else if(pcom.contains("execute_script")){
            pcom = pcom.trim();
            pcom = pcom.replace(" ", "");
            pcom = pcom.replace("   ", "");
            pcom = pcom.replace("execute_script","");
            try {
                Script sc = new Script();
                sc.execute_script(pcom);
            } catch (IOException e){
                System.out.println("Ошибка при выполнении скрипта!");
            }
        } else if(pcom.toLowerCase().equals("exit")){
            Commands.exit();
        } else if(pcom.toLowerCase().contains("replace_if_greater null")){
            pcom = pcom.trim();
            pcom = pcom.replace(" ", "");
            pcom = pcom.replace("   ", "");
            pcom = pcom.replace("replace_if_greaternull","");
            try {
                if_greater_than_null(Integer.parseInt(pcom));
            } catch (NumberFormatException e){
                System.out.println("Введено неккоректное значение команды! (ожидался Integer аргумент)");
            }
        } else if(pcom.toLowerCase().contains("remove_greater_key null")){
            pcom = pcom.trim();
            pcom = pcom.replace(" ", "");
            pcom = pcom.replace("   ", "");
            pcom = pcom.replace("remove_greater_keynull","");
            try{
                remove_if_greater_key_null(Integer.parseInt(pcom));
            } catch (NumberFormatException e){
                System.out.println("Введено неккоректное значение команды! (ожидался Integer аргумент)");
            }
        } else if(pcom.toLowerCase().contains("remove_lower_key null")){
            pcom = pcom.trim();
            pcom = pcom.replace(" ", "");
            pcom = pcom.replace("   ", "");
            pcom = pcom.replace("remove_lower_keynull","");
            try{
                remove_if_lover_key_null(Integer.parseInt(pcom));
            }catch (NumberFormatException e){
                System.out.println("Введено неккоректное значение команды! (ожидался Integer аргумент)");
            }
        } else if(pcom.toLowerCase().contains("count_less_than_type")){
            pcom = pcom.trim();
            pcom = pcom.replace(" ", "");
            pcom = pcom.replace("   ", "");
            pcom = pcom.replace("count_less_than_type","");
            count_less_than_type(TicketType.setTicketType(pcom));
        } else if(pcom.toLowerCase().equals("print_field_ascending_person")){
            Commands.print_field_ascending_person();
        } else if(pcom.toLowerCase().equals("print_field_descending_type")){
            Commands.print_field_descending_type();
        } else {
            System.out.println("Такой команды не существует!");
        }
    }

}
