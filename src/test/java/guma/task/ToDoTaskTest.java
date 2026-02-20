package guma.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class ToDoTaskTest {
    @Test
    public void toString_createTodoTask_returnTrue() {
        ToDoTask task = new ToDoTask("Read");
        assertEquals("[T][ ] Read", task.toString());
    }

    @Test
    public void toString_completeTask_returnTrue() {
        ToDoTask task = new ToDoTask("Write");
        task.complete();
        assertEquals("[T][X] Write", task.toString());
    }

    @Test
    public void toString_completeThenUndoTask_returnTrue() {
        ToDoTask task = new ToDoTask("Watch Single Inferno");
        task.complete();
        task.undo();
        assertEquals("[T][ ] Watch Single Inferno", task.toString());
    }

    @Test
    public void toFileString_completeThenUndoTask_returnTrue() {
        ToDoTask task = new ToDoTask("Buy milk");
        task.complete();
        task.undo();
        assertEquals("T_0_Buy milk", task.toFileString());
    }

    @Test
    public void toFileString_markComplete_encodesComplete() {
        ToDoTask task = new ToDoTask("Pay bills");
        task.complete();
        assertEquals("T_1_Pay bills", task.toFileString());
    }

    @Test
    public void getStatus_completeThenUndoTask_isIncomplete() {
        ToDoTask task = new ToDoTask("Exercise");
        task.complete();
        task.undo();
        assertFalse(task.getStatus());
    }

    @Test
    public void getStatus_markComplete_returnTrue() {
        ToDoTask task = new ToDoTask("Email");
        task.complete();
        assertTrue(task.getStatus());
    }

    @Test
    public void insideSchedule_todoTask_returnFalse() {
        ToDoTask task = new ToDoTask("Write");
        assertFalse(task.insideSchedule(LocalDate.of(2026, 1, 3)));
    }
}
