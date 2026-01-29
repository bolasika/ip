package guma.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DeadlineTaskTest {
    @Test
    public void toString_defaultStatus_formatsDate() {
        DeadlineTask task = new DeadlineTask("Submit report", "2/1/2026 1530");
        assertEquals("[D][ ] Submit report (by: Jan 02 2026 3.30PM)", task.toString());
    }

    @Test
    public void toString_nonDateDescription_keepsOriginal() {
        DeadlineTask task = new DeadlineTask("Renew license", "next week");
        assertEquals("[D][ ] Renew license (by: next week)", task.toString());
    }

    @Test
    public void toFileString_defaultStatus_encodesIncomplete() {
        DeadlineTask task = new DeadlineTask("File taxes", "2026-04-15 2359");
        assertEquals("D_0_File taxes_2026-04-15 2359", task.toFileString());
    }

    @Test
    public void toFileString_afterComplete_encodesComplete() {
        DeadlineTask task = new DeadlineTask("Pay rent", "2026-02-01 0900");
        task.complete();
        assertEquals("D_1_Pay rent_2026-02-01 0900", task.toFileString());
    }

    @Test
    public void status_afterUndo_isIncomplete() {
        DeadlineTask task = new DeadlineTask("Book flight", "2026-03-10 1200");
        task.complete();
        task.undo();
        assertFalse(task.getStatus());
    }

    @Test
    public void status_afterComplete_isComplete() {
        DeadlineTask task = new DeadlineTask("Pay bills", "2026-03-01 1200");
        task.complete();
        assertTrue(task.getStatus());
    }
}
