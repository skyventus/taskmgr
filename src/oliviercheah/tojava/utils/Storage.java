package oliviercheah.tojava.utils;
import oliviercheah.tojava.taskmanager.TaskManagerException;
import oliviercheah.tojava.taskmanager.Tasklist;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Storage {

    private String filePath;
    private Tasklist tasks;


    public Storage(String filePath){
        this.filePath = filePath;
        File directory = new File(filePath);
        if(! directory.exists())
            directory.mkdir();
    }

    public Tasklist load(String NewFilepath) throws IOException, TaskManagerException {
        //check directory
        this.filePath=NewFilepath;
        String[] task;
        File f = new File(filePath); // create a File for the given file path
        f.createNewFile();
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        int idx = 1;
        tasks = new Tasklist();
        if(! s.hasNext()){
            System.out.println("No tasks to load");
        }
        while(s.hasNext()){
            task = s.nextLine().split(Pattern.quote("|"));
            try{
                if (task[0].trim().equals("T")) {
                    tasks.addTask(Parser.createTodo(task[2]));
                } else if (task[0].trim().equals("D")) {
                    SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
                    Date date = df.parse(task[3].trim());
                    tasks.addTask(Parser.createDeadline(task[2].trim(), date));
                }
                if (task[1].contains("1")) {
                    tasks.completedTask(idx);
                }
                idx++;
            }catch (StringIndexOutOfBoundsException e){
                e.getMessage();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return this.tasks;
    }

    public void save(Tasklist tasks){
        FileWriter fw ;
        if(tasks.isEmpty()){
            System.out.println("[INFO] No tasks in the file to save");
        }else {
            try {
                fw = new FileWriter(filePath);
                for (int i = 0; i < tasks.getSize(); i++) {
                    fw.write(tasks.saveTask(i));
                }
                fw.close();
            } catch (IOException e) {
                e.getMessage();
            } catch (ArrayIndexOutOfBoundsException e) {
                e.getMessage();
            }
        }
    }

    public void listFiles(){
        File[] files = new File(filePath).listFiles();
        int i = 1;
        for(File file: files){
            if(file.isFile())
                System.out.println(i+". "+file.getName());

            i++;
        }

    }
}
