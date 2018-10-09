package com.company;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.*;

import java.util.Scanner;
import java.util.regex.Pattern;


public class TaskManager {

    private Ui ui;
    private Tasklist tasks;

    public TaskManager(String filepath){
        ui = new Ui();

    }

    public void run(){
        //print UI welcome message
        ui.printWelcome();


        String fullCommand = ui.readUserCommand().toLowerCase();
        String commandWord = Parser.getCommandWord(fullCommand);
        tasks = new Tasklist();
        while(!commandWord.equals("exit")) {
            try {
                switch (commandWord) {
                    case ("todo"):
                        tasks.addTask(Parser.createTodo(fullCommand));
                        break;
                    case ("deadline"):
                        String by = fullCommand.substring(fullCommand.indexOf("/by"));

                        tasks.addTask(Parser.createDeadline((fullCommand.substring(0, fullCommand.indexOf("/by")).trim()), by));
                        break;
                    case ("print"):
                        tasks.printTasks();
                        System.out.println(commandWord);
                        break;
                }
            }catch(StringIndexOutOfBoundsException e){
                System.out.println("Missing Parameter");
            }
            fullCommand = ui.readUserCommand().toLowerCase();
            commandWord = Parser.getCommandWord(fullCommand);
        }


       // System.out.println(Parser.createTodo(fullCommand));

       // tasks
    }

    public static void main(String[] args) {
        new TaskManager("data/tasks.txt").run();
    }
}
