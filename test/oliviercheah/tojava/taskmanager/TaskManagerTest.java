package oliviercheah.tojava.taskmanager;

import oliviercheah.tojava.taskmanager.Deadline;
import oliviercheah.tojava.Utils.Parser;
import oliviercheah.tojava.taskmanager.Todo;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

public class TaskManagerTest {

    @Test
    public void getCommandWord(){
        assertEquals("todo", Parser.getCommandWord("todo submit report"));
        assertEquals("deadline",Parser.getCommandWord("deadline submit report /by this thursday"));
        assertEquals("exit",Parser.getCommandWord("exit"));
        assertEquals("xyz",Parser.getCommandWord("  xyz "));
    }

    @Test
    public void createTodo(){
        Todo actual = Parser.createTodo("read book");
        Todo expected = new Todo("read book");
        assertEquals(expected.toString(), actual.toString());

    }

    @Test
    public void createDeadline(){
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        try {
            Date by = df.parse("11-11-2018");
            Deadline actual = Parser.createDeadline("submit book", by);
            Deadline expected = new Deadline("submit book", by);
            assertEquals(actual.toString(), expected.toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

}