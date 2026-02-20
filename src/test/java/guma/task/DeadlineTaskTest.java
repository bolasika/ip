package guma.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

public class DeadlineTaskTest {
    private final DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    @Test
    public void toString_creatingDeadlineTask_returnTrue() {
        DeadlineTask task = new DeadlineTask("Submit report",
                LocalDateTime.parse("2026-01-02 1530", fmt));
        assertEquals("[D][ ] Submit report (by: Jan 02 2026 3.30PM)", task.toString());
    }

    @Test
    public void toString_completingTask_returnTrue() {
        DeadlineTask task = new DeadlineTask("File taxes",
                LocalDateTime.parse("2026-04-15 2359", fmt));
        task.complete();
        assertEquals("[D][X] File taxes (by: Apr 15 2026 11.59PM)", task.toString());
    }

    @Test
    public void toString_completeThenUndoTask_returnTrue() {
        DeadlineTask task = new DeadlineTask("File taxes",
                LocalDateTime.parse("2026-04-15 2359", fmt));
        task.complete();
        task.undo();
        assertEquals("[D][ ] File taxes (by: Apr 15 2026 11.59PM)", task.toString());
    }

    @Test
    public void status_undoTask_success() {
        DeadlineTask task = new DeadlineTask("Book flight", LocalDateTime.parse("2026-03-10 1200", fmt));
        task.complete();
        task.undo();
        assertEquals(task.getStatus(), false);
    }

    @Test
    public void status_afterComplete_returnTrue() {
        DeadlineTask task = new DeadlineTask("Pay bills", LocalDateTime.parse("2026-03-01 1200", fmt));
        task.complete();
        assertTrue(task.getStatus());
    }

    @Test
    public void toFileString_convertingTaskToString_returnTrue() {
        DeadlineTask task = new DeadlineTask("Submit report",
                LocalDateTime.parse("2026-01-02 1530", fmt));
        assertEquals("D_0_Submit report_2/1/2026 1530", task.toFileString());
    }

    @Test
    public void toFileString_convertingCompletedTaskToString_returnTrue() {
        DeadlineTask task = new DeadlineTask("Pay rent",
                LocalDateTime.parse("2026-02-01 0900", fmt));
        task.complete();
        assertEquals("D_1_Pay rent_1/2/2026 0900", task.toFileString());
    }

    @Test
    public void insideSchedule_sameDate_returnTrue() {
        DeadlineTask task = new DeadlineTask("Submit report",
                LocalDateTime.parse("2026-01-02 1530", fmt));
        assertTrue(task.insideSchedule(LocalDate.of(2026, 1, 2)));
    }

    @Test
    public void insideSchedule_differentDate_success() {
        DeadlineTask task = new DeadlineTask("Submit report",
                LocalDateTime.parse("2026-01-02 1530", fmt));
        assertEquals(task.insideSchedule(LocalDate.of(2026, 1, 3)), false);
    }
}
