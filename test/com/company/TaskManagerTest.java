package com.company;

import oliviercheah.tojava.taskmanager.Deadline;
import oliviercheah.tojava.Utils.Parser;
import oliviercheah.tojava.taskmanager.Todo;
import org.junit.Test;

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
        assertEquals(actual, expected);
    }

    @Test
    public void createDeadline(){
        Deadline actual = Parser.createDeadline("submit book", "this friday");
        Deadline expected = new Deadline("submit book", "this friday");
        assertEquals(actual, expected);
    }

}