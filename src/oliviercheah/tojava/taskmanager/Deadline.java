package oliviercheah.tojava.taskmanager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Deadline extends Todo {

    private Date deadline;

    public Deadline(String task, Date deadline){
        super(task);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        return super.toString() + System.lineSeparator() + "do by: " + df.format(deadline);
    }

    @Override
    public String saveTask(){
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        return "D | " + (super.isDone() ? "1" : "0") + " | " + super.getDescription() + " | " + df.format(deadline) + "\n";
    }

    @Override
    public boolean nearDeadline(){
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Date today= new Date();
        long diff =  deadline.getTime()-today.getTime();
        int diffDays=(int) (diff/(24*60*60*1000));

        if(diffDays<=7)
            return  true;
        else
            return false;
    }

    @Override
    public int getDueDays(){
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Date today= new Date();
        long diff =  deadline.getTime()-today.getTime();
        int diffDays=(int) (diff/(24*60*60*1000));

        return diffDays;
    }
}
