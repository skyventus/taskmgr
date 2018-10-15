package oliviercheah.tojava.taskmanager;

public class Todo extends Task {

    private boolean isDone;

    public Todo(String Desc){
        super(Desc);
        isDone=false;
    }

    public boolean isDone(){
        return this.isDone;
    }

    public void setDone(boolean d){
        this.isDone=d;
    }

    @Override
    public String toString() {
        return super.toString() + System.lineSeparator() +  "Is Done? " + (isDone ? "Yes" : "No");
    }

    @Override
    public String save(){
        return "T | " + (isDone ? "1" : "0")+ " | " + super.save() + "\n";
    }



//    @Override
//    public void printTask(Tasklist task){
//        //[1] submit report
//        // super.printTask(task) + System.lineSeparator() +  "Is Done? " + (isDone ? "Yes" : "No");
//        // System.out.println(Arrays.toString(task.taskDesc.toArray()));
//    }
}
