package guma.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

public class EventTaskTest {
    private final DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    @Test
    public void toString_createEventTask_returnTrue() {
        EventTask task = new EventTask("Team meeting",
                LocalDateTime.parse("2026-01-02 1530", fmt),
                LocalDateTime.parse("2026-01-02 1800", fmt));
        assertEquals("[E][ ] Team meeting (from: Jan 02 2026 3.30PM to: Jan 02 2026 6.00PM)", task.toString());
    }

    @Test
    public void toString_afterComplete_returnTrue() {
        EventTask task = new EventTask("Workshop",
                LocalDateTime.parse("2026-02-10 0900", fmt),
                LocalDateTime.parse("2026-02-10 1700", fmt));
        task.complete();
        assertEquals("[E][X] Workshop (from: Feb 10 2026 9.00AM to: Feb 10 2026 5.00PM)", task.toString());
    }

    @Test
    public void toString_startLaterThanEnd_returnTrue() {
        EventTask task = new EventTask("Backwards event",
                LocalDateTime.parse("2026-01-03 1700", fmt),
                LocalDateTime.parse("2026-01-02 0900", fmt));
        assertEquals("[E][ ] Backwards event (from: Jan 03 2026 5.00PM to: Jan 02 2026 9.00AM)", task.toString());
    }

    @Test
    public void toFileString_convertingTaskToString_returnTrue() {
        EventTask task = new EventTask("Team meeting",
                LocalDateTime.parse("2026-01-02 1530", fmt),
                LocalDateTime.parse("2026-01-02 1800", fmt));
        assertEquals("E_0_Team meeting_2/1/2026 1530_2/1/2026 1800", task.toFileString());
    }

    @Test
    public void toFileString_convertingCompletedTaskToString_returnTrue() {
        EventTask task = new EventTask("Workshop",
                LocalDateTime.parse("2026-02-10 0900", fmt),
                LocalDateTime.parse("2026-02-10 1700", fmt));
        task.complete();
        assertEquals("E_1_Workshop_10/2/2026 0900_10/2/2026 1700", task.toFileString());
    }

    @Test
    public void insideSchedule_sameDay_returnTrue() {
        EventTask task = new EventTask("Team meeting",
                LocalDateTime.parse("2026-01-02 1530", fmt),
                LocalDateTime.parse("2026-01-02 1800", fmt));
        assertTrue(task.insideSchedule(LocalDate.of(2026, 1, 2)));
    }

    @Test
    public void insideSchedule_betweenDays_returnTrue() {
        EventTask task = new EventTask("Conference",
                LocalDateTime.parse("2026-01-02 0900", fmt),
                LocalDateTime.parse("2026-01-04 1700", fmt));
        assertTrue(task.insideSchedule(LocalDate.of(2026, 1, 3)));
    }

    @Test
    public void insideSchedule_outsideRange_returnFalse() {
        EventTask task = new EventTask("Conference",
                LocalDateTime.parse("2026-01-02 0900", fmt),
                LocalDateTime.parse("2026-01-04 1700", fmt));
        assertFalse(task.insideSchedule(LocalDate.of(2026, 1, 5)));
    }
}
