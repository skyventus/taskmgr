package com.company;
import java.io.IOException;
import java.lang.*;


public class TaskManager {

    private Ui ui;
    private Tasklist tasks;
    private Storage storage;

    public TaskManager(String filepath){
        ui = new Ui();
        System.out.println(filepath);
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
                        tasks.addTask(Parser.createTodo(Description));
                        break;
                    case ("deadline"):
                        String by = fullCommand.substring(fullCommand.indexOf("/by")).replace("/by", "").trim();
                        Description=fullCommand.replace(commandWord, "").substring(0, fullCommand.indexOf("/by")).trim();
                        tasks.addTask(Parser.createDeadline(Description, by));
                        break;
                    case ("print"):
                        tasks.printTasks();
                        break;
                    case ("done"):
                        int idx = Integer.parseInt(fullCommand.replace("done", "").trim());
                        tasks.completedTask(idx);
                        break;
                    case ("save"):
                        storage.save(tasks);
                        break;
                }
            }catch(StringIndexOutOfBoundsException e){
                Ui.printError("Missing Parameter");
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
