/**
 * Класс для парсинга Java -> XML
 * @author Дмитрий Толочек P3130
 * @version 1.0 Before Check
 */

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

class ParserWriter {
    /**
     * Метод для задания версии XML
     * @return - первая строка XML
     */
    static StringBuilder XMLVesrion(){
        StringBuilder ret = new StringBuilder();
        ret.append("<?xml version = \"1.0\"?>");
        return ret;
    }

    /**
     * Метод добавляющий стартовый тег в XML разметку
     * @param el - тег
     * @param lvl - уровень отступа (На будущее)
     * @return - строка для XML файла
     */
    static StringBuilder addStartElement(String el, int lvl){
        StringBuilder ret = new StringBuilder();
        if(lvl > 1){ret.append(ParserWriter.repeat(Math.max(0, lvl)));} else if (lvl > 0){ret.append("    ");}
        ret.append(String.format("<%1$s>", el));
        return ret;
    }

    /**
     * Метод добавляющий закрывающий тег в XML разметку
     * @param el - тег
     * @param lvl - уровень отступа (На будущее)
     * @return - строка для XML файла
     */
    static StringBuilder addEndElement(String el, int lvl){
        StringBuilder ret = new StringBuilder();
        if(lvl > 1){ret.append(ParserWriter.repeat(Math.max(0, lvl)));} else if (lvl > 0){ret.append("    ");}
        ret.append(String.format("</%1$s>", el));
        return ret;
    }

    /**
     * Добавляет в разметку XML значение, обернутое тегами
     * @param el - тег
     * @param value - значение
     * @param lvl - уровень отступа (На будущее)
     * @return - строка для XML файла
     */
    static StringBuilder addElementWithValue(String el, String value, int lvl){
        StringBuilder ret = new StringBuilder();
        if(lvl > 1){ret.append(ParserWriter.repeat(Math.max(0, lvl)));} else if (lvl > 0){ret.append("    ");}
        ret.append(String.format("<%1$s>%2$s</%1$s>", el, value));
        return ret;
    }

    /**
     * Метод реализующий добавление отпаршенных строк в файл
     * @param file - ссылка на файл
     * @param xml - тег xml
     */
    static void addParseStrToFile(String file, StringBuilder xml){
        try{
            xml.append("\n");
            FileOutputStream out = new FileOutputStream(file, true);
            BufferedOutputStream bos = new BufferedOutputStream(out);
            byte[] buffer = xml.toString().getBytes();
            bos.write(buffer, 0, buffer.length);
            bos.flush();
        } catch (IOException e){
            System.out.println("Произошла ошибка во время записи в файл!");
        }
    }

    /**
     * Метод, предназначенный для отчистки файла
     * @param file - ссылка на файл
     */
    static void cleanFile(String file){
        try{
            FileOutputStream out = new FileOutputStream(file);
            BufferedOutputStream bos = new BufferedOutputStream(out);
            byte[] buffer = "".getBytes();
            bos.write(buffer, 0, buffer.length);
            bos.flush();
        } catch (IOException e){
            System.out.println("Произошла ошибка во время записи в файл!");
        }
    }

    /**
     * Изначально прога делалась на JDK 11, а потом вспомнил, что нужен 8. По сути, это реализация метода repeat.
     * @param val - кол-во повторений
     * @return - строка, повторенная несколько раз
     */
    private static String repeat(int val){
        StringBuilder result = new StringBuilder();
        for (int i = 1; i <= val; i++){
            result.append("    ");
        }
        return "    ";
    }
}
