package oliviercheah.tojava.taskmanager;

public class Todo extends Task {

    private boolean done;

    public Todo(String Desc){
        super(Desc);
        done=false;
    }

    @Override
    public boolean isDone(){
        return this.done;
    }

    @Override
    public void setDone(boolean d){
        this.done=d;
    }

    @Override
    public String toString() {
        return super.toString() + System.lineSeparator() +  "Is Done? " + (done ? "Yes" : "No");
    }

    @Override
    public boolean nearDeadline(){
        return false;
    }

    @Override
    public int getDueDays(){
        return 0;
    }

    @Override
    public String saveTask(){
        return "T | " + (done ? "1" : "0")+ " | " + super.description + "\n";
    }



//    @Override
//    public void printTask(Tasklist task){
//        //[1] submit report
//        // super.printTask(task) + System.lineSeparator() +  "Is Done? " + (isDone ? "Yes" : "No");
//        // System.out.println(Arrays.toString(task.taskDesc.toArray()));
//    }
}
