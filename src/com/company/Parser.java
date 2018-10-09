package com.company;

public class Parser {

    public static String getCommandWord(String fullCommand){
        String[] task;

        fullCommand.toLowerCase();

        task = fullCommand.split(" ");
        try {
             if(task[0].equals("todo"))
                 return "todo";
        }catch(ArrayIndexOutOfBoundsException e){
            Ui.printError(e.getMessage());
        }
        return task[0];
    }

    public static Todo createTodo(String fullCommand){
        Todo todoTask = new Todo(fullCommand.replace("todo", "").trim());
        return todoTask;
    }

    public static Deadline createDeadline(String fullCommand, String by){
        Deadline deadline = new Deadline(fullCommand.replace("deadline", "").substring(0, fullCommand.indexOf("/by")).trim(), by);
        return deadline;
    }
}
