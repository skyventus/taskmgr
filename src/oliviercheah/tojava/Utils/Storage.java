package oliviercheah.tojava.Utils;
import oliviercheah.tojava.taskmanager.TaskManagerException;
import oliviercheah.tojava.taskmanager.Tasklist;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Storage {

    private String filePath;
    private Tasklist tasks;


    public Storage(String filePath){
        this.filePath = filePath;
    };

    public Tasklist load() throws IOException, TaskManagerException {
        String[] task;
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        int idx = 1;
        tasks = new Tasklist();
        if(! s.hasNext()){
            throw new TaskManagerException("Error reading the file");
        }
        while(s.hasNext()){
            task = s.nextLine().split(Pattern.quote("|"));
            try{
                if (task[0].trim().equals("T")) {
                    tasks.addTask(Parser.createTodo(task[2]));
                } else if (task[0].trim().equals("D")) {
                    tasks.addTask(Parser.createDeadline(task[2].trim(), task[3].trim()));
                }
                if (task[1].contains("1")) {
                    tasks.completedTask(idx);
                }
                idx++;
            }catch (StringIndexOutOfBoundsException e){
                e.getMessage();
            }
        }
        return this.tasks;
    }

    public void save(Tasklist tasks){
        FileWriter fw = null;
        try {
            fw = new FileWriter(filePath);
            for(int i=0; i<tasks.getSize(); i++){
                fw.write(tasks.saveTask(i));
            }
            fw.close();
        } catch (IOException e) {
            e.getMessage();
        } catch (ArrayIndexOutOfBoundsException e){
            e.getMessage();
        }
    }
}
