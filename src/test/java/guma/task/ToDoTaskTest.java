package guma.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ToDoTaskTest {
    @Test
    public void toString_defaultStatus_showsIncomplete() {
        ToDoTask task = new ToDoTask("Read");
        assertEquals("[T][ ] Read", task.toString());
    }

    @Test
    public void toString_markComplete_showsComplete() {
        ToDoTask task = new ToDoTask("Write");
        task.complete();
        assertEquals("[T][X] Write", task.toString());
    }

    @Test
    public void toFileString_defaultStatus_encodesIncomplete() {
        ToDoTask task = new ToDoTask("Buy milk");
        assertEquals("T_0_Buy milk", task.toFileString());
    }

    @Test
    public void toFileString_markComplete_encodesComplete() {
        ToDoTask task = new ToDoTask("Pay bills");
        task.complete();
        assertEquals("T_1_Pay bills", task.toFileString());
    }

    @Test
    public void status_afterUndo_isIncomplete() {
        ToDoTask task = new ToDoTask("Exercise");
        task.complete();
        task.undo();
        assertFalse(task.getStatus());
    }

    @Test
    public void status_markComplete_isComplete() {
        ToDoTask task = new ToDoTask("Email");
        task.complete();
        assertTrue(task.getStatus());
    }
}
