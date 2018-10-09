package com.company;
import java.util.ArrayList;
import java.util.List;

public class Tasklist {

    private List<Task> tasks;

    public Tasklist(){
        this.tasks = new ArrayList<>();
    }


    public void addTask(Task task){
        //tasks.add(task);
        tasks.add(task);
        System.out.println("Tasks in the list: " + tasks.size());
    }

    public String saveTask(int idx){
        String value = tasks.get(idx).save();
        return value;
    }

    public int getSize(){
        return tasks.size();
    }
    public void printTasks() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("[" + (i + 1) + "] " + tasks.get(i).toString());
        }
    }

    public void completedTask(int idx){
        tasks.get(idx-1).setDone(true);
    }
}
