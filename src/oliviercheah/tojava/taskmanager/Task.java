package oliviercheah.tojava.taskmanager;

public abstract class Task {
    protected String description;
    //protected boolean isDone;

    public Task(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void updateTask(String description){this.description = description;}

    public abstract boolean isDone();

    public abstract void setDone(boolean isDone);
//    public abstract String getBy();
    @Override
    public String toString() {
        return "description: " + description;
    }
    public abstract boolean nearDeadline();
    public abstract String save();
    public abstract int getDueDays();
}