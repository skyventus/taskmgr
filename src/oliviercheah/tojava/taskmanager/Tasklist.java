package oliviercheah.tojava.taskmanager;
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
        //System.out.println("Tasks in the list: " + tasks.size());
    }

    public String saveTask(int idx){
        String value = tasks.get(idx).save();
        return value;
    }

    public int getSize(){
        return tasks.size();
    }

    public void printTasks() {
        if(tasks.isEmpty())
            System.out.println("No tasks has been added to the list yet.");
        int i=0;

        for(Task task: tasks) {
            System.out.println("[" + (i + 1) + "] " + task.toString());
            i++;
        }
    }

    public boolean isEmpty(){
        return tasks.isEmpty();
    }

    public void completedTask(int idx){
        tasks.get(idx-1).setDone(true);
    }

    public int numberOfCompletedTask(){
        int numberCompletedTask=0;
        for(Task task: tasks)
            if(task.isDone())
                numberCompletedTask++;

        return numberCompletedTask;
    }

    public int numberOfInompletedTask(){
        int numberInompletedTask=0;

        for(Task task: tasks)
            if(!task.isDone())
                numberInompletedTask++;

        return numberInompletedTask;
    }

    public void updateTask(int idx, String desc){
        tasks.get(idx-1).updateTask(desc);
        System.out.println("Update completed");
    }

    public void getExpiry() {
        for(Task task: tasks) {
            if(task.nearDeadline() && !task.isDone()){
                System.out.println("Task [" + (tasks.indexOf(task)+1)+"]: " + System.lineSeparator() + task.toString() + System.lineSeparator() + " deadline is less than 7 days");
            }
        }
    }
}
