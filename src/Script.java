/**
 * Класс для работы со скриптами приложения
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

class Script {
    public File file;
    public BufferedReader reader;
    public String line;
    public static ArrayList files = new ArrayList();
    public String filePath;

    /**
     * Метод запускает скрипт
     * @param scriptPath - файл
     * @throws IOException - ошибки с файлом
     */
    void execute_script(String scriptPath) throws IOException {
        try {
            this.filePath = scriptPath;
            if(files.contains(this.filePath)){
                System.out.println("В целях безопасности данных в программе.");
                System.out.println("Программа не может снова начать выполнение скрипта, который исполняет прямо сейчас!");
            } else {
                files.add(this.filePath);
                this.file = new File(this.filePath);
                FileReader fileReader = new FileReader(file);
                this.reader = new BufferedReader(fileReader);
                this.line = this.reader.readLine();
                while (this.line != null){
                    Commands.catchAndExecuteCommand(line);
                    this.line = this.reader.readLine();
                }
                this.reader.close();
                files.clear();
            }
        } catch (IOException e){
            System.out.println("Произошла ошибка при чтении скрипта!");
        }
    }
}
