package oliviercheah.tojava.taskmanager;
import oliviercheah.tojava.Utils.Parser;
import oliviercheah.tojava.Utils.Storage;
import oliviercheah.tojava.Utils.Ui;

import java.io.IOException;
import java.lang.*;


public class TaskManager {

    private Ui ui;
    private Tasklist tasks;
    private Storage storage;

    public TaskManager(String filepath){
        ui = new Ui();
        storage = new Storage(filepath);
        try {
            tasks = storage.load();
        } catch (TaskManagerException e) {
            ui.showToUser("Problem reading file. Starting with an empty task list");
            tasks = new Tasklist();
        } catch (IOException e) {
            ui.printError(e.getMessage());
        }
    }

    public void run(){
        //print UI welcome message
        ui.printWelcome();

        String Description;
        String fullCommand = ui.readUserCommand().toLowerCase();
        String commandWord = Parser.getCommandWord(fullCommand);
        while(!commandWord.equals("exit")) {
            try {
                switch (commandWord) {
                    case ("todo"):
                        Description=fullCommand.replace(commandWord, "");
                        if(Description.isEmpty())
                            throw new TaskManagerException("Error: Did not input any tasks");
                        tasks.addTask(Parser.createTodo(Description));
                        break;
                    case ("deadline"):
                        String by = fullCommand.substring(fullCommand.indexOf("/by")).replace("/by", "").trim();
                        Description=fullCommand.substring(0, fullCommand.indexOf("/by")).replace(commandWord, "").trim();
                        if(Description.isEmpty())
                            throw new TaskManagerException("Error: Did not input any tasks");
                        System.out.println(Description);
                        tasks.addTask(Parser.createDeadline(Description, by));
                        break;
                    case ("print"):
                        tasks.printTasks();
                        break;
                    case ("done"):
                        int idx = Integer.parseInt(fullCommand.replace("done", "").trim());
                        if(tasks.isEmpty())
                            throw new TaskManagerException("WARNING: The list is empty. Nothing to be mark as done.");
                        tasks.completedTask(idx);
                        break;
                    case ("save"):
                        if(tasks.isEmpty())
                            throw new TaskManagerException("WARNING: The list is empty. No tasks to be saved.");
                        storage.save(tasks);
                        break;
                }
            }catch(StringIndexOutOfBoundsException e){
                Ui.printError("ERROR: Please remember to add in /by <when is the deadline>.");
            } catch (TaskManagerException e) {
                Ui.printError(e.getMessage());
            }catch(NumberFormatException e){
                Ui.printError("ERROR: Please key in the task number that you want to mark as complete.");
            }

            fullCommand = ui.readUserCommand().toLowerCase();
            commandWord = Parser.getCommandWord(fullCommand);
        }


       // System.out.println(Parser.createTodo(fullCommand));

       // tasks
    }

    public static void main(String[] args) {
        new TaskManager("data/test.txt").run();
    }
}
