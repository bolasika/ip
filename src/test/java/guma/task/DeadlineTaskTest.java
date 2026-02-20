package guma.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

public class DeadlineTaskTest {
    private final DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    @Test
    public void toString_defaultStatus_formatsDate() {
        DeadlineTask task = new DeadlineTask("Submit report",
                LocalDateTime.parse("2026-01-02 1530", fmt));
        assertEquals("[D][ ] Submit report (by: Jan 02 2026 3.30PM)", task.toString());
    }

    @Test
    public void toString_nonDateDescription_keepsOriginal() {
        DeadlineTask task = new DeadlineTask("Renew license",
                LocalDateTime.parse("2026-01-02 1530", fmt));
        assertEquals("[D][ ] Renew license (by: Jan 02 2026 3.30PM)", task.toString());
    }

    @Test
    public void status_afterUndo_isIncomplete() {
        DeadlineTask task = new DeadlineTask("Book flight", LocalDateTime.parse("2026-03-10 1200", fmt));
        task.complete();
        task.undo();
        assertFalse(task.getStatus());
    }

    @Test
    public void status_afterComplete_isComplete() {
        DeadlineTask task = new DeadlineTask("Pay bills", LocalDateTime.parse("2026-03-01 1200", fmt));
        task.complete();
        assertTrue(task.getStatus());
    }
}
