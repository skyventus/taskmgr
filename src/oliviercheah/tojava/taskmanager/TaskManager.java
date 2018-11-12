package oliviercheah.tojava.taskmanager;
import oliviercheah.tojava.utils.Parser;
import oliviercheah.tojava.utils.Storage;
import oliviercheah.tojava.utils.Ui;

import java.io.IOException;
import java.lang.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class TaskManager {

    private Ui ui;
    private Tasklist tasks;
    private Storage storage;


    public TaskManager(String filepath){
        ui = new Ui();
        storage = new Storage(filepath);
        storage.listFiles();
        Ui.showToUser("Please key in the filename or the new filename that you want to create/load.");

        String choice = ui.readUserChoice().toLowerCase();

        while(choice.isEmpty()) {
                Ui.showToUser("Please key in the filename or the new filename that you want to create/load.");
                choice = ui.readUserChoice().toLowerCase();
        }try {
                if(! choice.contains(".txt") && !choice.contains("exit"))
                    choice = choice+".txt";
                else {
                    System.exit(0);
                }
                tasks=storage.load(filepath+"/"+choice);
                Ui.showToUser("-Number of Completed Task: " + tasks.numberOfCompletedTask());
                Ui.showToUser("--Number of Incompleted Task: " + tasks.numberOfInompletedTask());
                Ui.showToUser("---Total number of task recorded: " + tasks.getSize());
                tasks.getExpiry();
            } catch (IOException e) {
                Ui.printError("{WARNING]: Unable to locate the file. New file has been generated in data folder");
            } catch (TaskManagerException e) {
                Ui.printError("{ERROR]: Unable to locate the file, no tasks has been added.");
        }
    }

    public void run(){
        //print UI welcome message
        ui.printWelcome();

        String Description;
        int idx;
        String fullCommand = ui.readUserCommand().toLowerCase();
        String commandWord = Parser.getCommandWord(fullCommand);
        while(!commandWord.equals("exit")) {
            try {
                switch (commandWord) {
                    case ("todo"):
                        Description=fullCommand.replace(commandWord, "");
                        if(Description.isEmpty())
                            throw new TaskManagerException("[ERROR] Did not input any tasks");
                        tasks.addTask(Parser.createTodo(Description));
                        Ui.showToUser("Todo has been successfully added.");
                        break;
                    case ("deadline"):
                        String by = fullCommand.substring(fullCommand.indexOf("/by")).replace("/by", "").trim();
                        Description=fullCommand.substring(0, fullCommand.indexOf("/by")).replace(commandWord, "").trim();
                        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
                        Date date;
                        try{
                            if(Description.isEmpty())
                                throw new TaskManagerException("[ERROR] Did not input any tasks");
                            //System.out.println(Description);
                            date = df.parse(by);
                            tasks.addTask(Parser.createDeadline(Description, date));
                            Ui.showToUser("Deadline Task has been successfully added.");
                        }catch(ParseException e){
                            Ui.printError("[ERROR] Please ensure the Date format is dd-mm-yyyy");
                        }
                        break;
                    case ("print"):
                        if(tasks.isEmpty())
                            throw new TaskManagerException("[WARNING] The list is empty. Nothing to be print.");
                        tasks.printTasks();
                        break;
                    case ("update"):
                        idx = Integer.parseInt(fullCommand.replace("update","").trim());
                        Ui.showToUser("What is the new task?");
                        Description=ui.readUserCommand();
                        tasks.updateTask(idx, Description);
                        break;
                    case ("done"):
                        idx = Integer.parseInt(fullCommand.replace("done", "").trim());
                        if(tasks.isEmpty())
                            throw new TaskManagerException("[WARNING] The list is empty. Nothing to be mark as done.");
                        tasks.completedTask(idx);
                        break;
                    case ("save"):
                        if(tasks.isEmpty()) {
                            Ui.showToUser("[INFO] No task in list to be saved.");
                            break;
                        }
                        storage.save(tasks);
                        Ui.showToUser("All tasks has been saved.");
                        break;
                    case ("delete"):
                        if(tasks.isEmpty())
                            throw new TaskManagerException("[WARNING] The list is empty. No tasks to be deleted.");
                        idx = Integer.parseInt(fullCommand.replace("delete", "").trim());
                        tasks.deleteTask(idx);
                        break;
                    case ("help"):
                        Ui.showCommands();
                        break;
                    default:
                        Ui.showToUser("[WARNING] No such command exist.");
                        break;
                }
            }catch(StringIndexOutOfBoundsException e){
                Ui.printError("[ERROR] Please remember to add in /by <when is the deadline>.");
            } catch (TaskManagerException e) {
                Ui.printError(e.getMessage());
            }catch(NumberFormatException e){
                Ui.printError("[ERROR] Please key in the task number that you want to mark as complete.");
            }

            fullCommand = ui.readUserCommand().toLowerCase();
            commandWord = Parser.getCommandWord(fullCommand);
        }

        if(tasks.isEmpty()) {
            Ui.showToUser("[INFO] No task in list to be saved.");
        }else {
            Ui.showToUser("You have exited the TaskManager. All your tasks has been saved.");
            storage.save(tasks);
        }



        // System.out.println(Parser.createTodo(fullCommand));

        // tasks
    }

    public static void main(String[] args) {
        new TaskManager("data").run();
    }
}
