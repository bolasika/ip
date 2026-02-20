package guma;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import guma.command.AddCommand;
import guma.command.Command;
import guma.command.CompleteCommand;
import guma.command.DeleteCommand;
import guma.command.ExitCommand;
import guma.command.FindCommand;
import guma.command.HelpCommand;
import guma.command.ListCommand;
import guma.command.ScheduleCommand;
import guma.command.UndoCommand;
import guma.exception.GumaException;
import guma.exception.GumaInvalidCommand;
import guma.exception.GumaInvalidDateTimeException;
import guma.exception.GumaInvalidDateTimeRangeException;


public class ParserTest {

    @Test
    public void parse_eventEndBeforeStart_throwsException() {
        String input = "event Conference /from 2026-01-03 1700 /to 2026-01-02 0900";
        assertThrows(GumaInvalidDateTimeRangeException.class, () -> Parser.parse(input));
    }

    @Test
    public void dateTimeChecker_slashDmy_returnTrue() throws Exception {
        LocalDateTime expected = LocalDateTime.of(2026, 1, 2, 15, 30);
        assertEquals(expected, Parser.dateTimeChecker("2/1/2026 1530"));
    }

    @Test
    public void dateTimeChecker_dashYmd_returnTrue() throws Exception {
        LocalDateTime expected = LocalDateTime.of(2026, 1, 2, 15, 30);
        assertEquals(expected, Parser.dateTimeChecker("2026-01-02 1530"));
    }

    @Test
    public void dateTimeChecker_invalidFormat_throwsException() {
        assertThrows(GumaInvalidDateTimeException.class, () -> Parser.dateTimeChecker("2026/01/02 1530"));
    }

    @Test
    public void dateToString_returnTrue() {
        LocalDateTime dateTime = LocalDateTime.of(2026, 1, 2, 15, 30);
        assertEquals("Jan 02 2026 3.30PM", Parser.dateToString(dateTime));
    }

    @Test
    public void dateToSave_returnTrue() {
        LocalDateTime dateTime = LocalDateTime.of(2026, 1, 2, 15, 30);
        assertEquals("2/1/2026 1530", Parser.dateToSave(dateTime));
    }

    @Test
    public void parse_listCommand_returnTrue() throws Exception {
        Command command = Parser.parse("list");
        assertEquals(ListCommand.class, command.getClass());
    }

    @Test
    public void parse_byeCommand_returnTrue() throws Exception {
        Command command = Parser.parse("bye");
        assertEquals(ExitCommand.class, command.getClass());
    }

    @Test
    public void parse_markCommand_returnTrue() throws Exception {
        Command command = Parser.parse("mark 2");
        assertEquals(CompleteCommand.class, command.getClass());
    }

    @Test
    public void parse_markCommandWithInvalidSyntax_throwsException() {
        GumaException ex = assertThrows(GumaInvalidCommand.class, () -> Parser.parse("mark invalid"));
        assertEquals(">> You sure you type correctly ah?\nThe syntax for this command should be:\nmark <task_index>",
                ex.getMessage());
    }

    @Test
    public void parse_unmarkCommand_returnTrue() throws Exception {
        Command command = Parser.parse("unmark 3");
        assertEquals(UndoCommand.class, command.getClass());
    }

    @Test
    public void parse_unmarkCommandInvalidSyntax_throwsException() {
        GumaException ex = assertThrows(GumaInvalidCommand.class, () -> Parser.parse("unmark INVALID"));
        assertEquals(">> You sure you type correctly ah?\nThe syntax for this command should be:\nunmark <task_index>",
                ex.getMessage());
    }

    @Test
    public void parse_deleteCommand_returnTrue() throws Exception {
        Command command = Parser.parse("delete 1");
        assertEquals(DeleteCommand.class, command.getClass());
    }

    @Test
    public void parse_deleteCommandInvalidSyntax_throwsException() {
        GumaException ex = assertThrows(GumaInvalidCommand.class, () -> Parser.parse("delete INVALID"));
        assertEquals(">> You sure you type correctly ah?\nThe syntax for this command should be:\ndelete <task_index>",
                ex.getMessage());
    }

    @Test
    public void parse_todoCommand_returnTrue() throws Exception {
        Command command = Parser.parse("todo Read book");
        assertEquals(AddCommand.class, command.getClass());
    }

    @Test
    public void parse_todoCommandInvalidSyntax_throwsException() {
        GumaException ex = assertThrows(GumaInvalidCommand.class, () -> Parser.parse("todo"));
        assertEquals(">> You sure you type correctly ah?\nThe syntax for this command should be:\ntodo <taskname>",
                ex.getMessage());
    }

    @Test
    public void parse_deadlineCommand_returnTrue() throws Exception {
        Command command = Parser.parse("deadline Submit /by 2026-01-02 1530");
        assertEquals(AddCommand.class, command.getClass());
    }

    @Test
    public void parse_deadlineCommandInvalidDate_returnTrue() throws Exception {
        assertThrows(GumaInvalidDateTimeException.class, () ->
                Parser.parse("deadline Submit /by 2026-01-022 1530"));
    }

    @Test
    public void parse_deadlineCommandInvalidSyntax_throwsException() {
        GumaException ex = assertThrows(GumaInvalidCommand.class, () -> Parser.parse("deadline Submit"));
        assertEquals(">> You sure you type correctly ah?\nThe syntax for this command should be:\n"
                        + "deadline <taskname> /by <DateTime>\n"
                        + "<DateTime> got format one ah: dd/MM/yyyy HHmm or yyyy-MM-dd HHmm",
                ex.getMessage());
    }

    @Test
    public void parse_eventCommand_returnTrue() throws Exception {
        Command command = Parser.parse("event Conference /from 2026-01-02 0900 /to 2026-01-02 1700");
        assertEquals(AddCommand.class, command.getClass());
    }

    @Test
    public void parse_eventCommandStartAfterEnd_throwsException() {
        GumaException ex = assertThrows(GumaInvalidDateTimeRangeException.class, () ->
                Parser.parse("event Conference /from 2026-01-03 1700 /to 2026-01-02 0900"));
        assertEquals(">> The end date-time cannot be before the start date-time.", ex.getMessage());
    }

    @Test
    public void parse_eventCommandInvalidSyntax_throwsException() {
        GumaException ex = assertThrows(GumaInvalidCommand.class, () ->
                Parser.parse("event Conference /from 2026-01-02 0900"));
        assertEquals(">> You sure you type correctly ah?\nThe syntax for this command should be:\n"
                        + "event <taskname> /from <DateTime> /to <DateTime>\n"
                        + "<DateTime> got format one ah: dd/MM/yyyy HHmm or yyyy-MM-dd HHmm",
                ex.getMessage());
    }

    @Test
    public void parse_findCommand_returnTrue() throws Exception {
        Command command = Parser.parse("find book");
        assertEquals(FindCommand.class, command.getClass());
    }

    @Test
    public void parse_findCommandInvalidSyntax_throwsException() {
        GumaException ex = assertThrows(GumaInvalidCommand.class, () -> Parser.parse("find"));
        assertEquals(">> You sure you type correctly ah?\nThe syntax for this command should be:\nfind <keyword>",
                ex.getMessage());
    }

    @Test
    public void parse_scheduleCommand_returnTrue() throws Exception {
        Command command = Parser.parse("schedule /on 02/01/2026");
        assertEquals(ScheduleCommand.class, command.getClass());
    }

    @Test
    public void parse_scheduleCommandInvalidSyntax_throwsException() {
        GumaException ex = assertThrows(GumaInvalidCommand.class, () -> Parser.parse("schedule"));
        assertEquals(">> You sure you type correctly ah?\n"
                        +
                        "The syntax for this command should be:\nschedule /on dd/MM/yyyy",
                ex.getMessage());
    }

    @Test
    public void parse_unknownCommand_throwsException() {
        GumaException ex = assertThrows(GumaInvalidCommand.class, () -> Parser.parse("unknown"));
        assertEquals(">> Wah lao, what you mean?\nRun help command if you don't know lei...", ex.getMessage());
    }

    @Test
    public void parse_helpCommand_returnTrue() throws Exception {
        Command command = Parser.parse("help");
        assertEquals(HelpCommand.class, command.getClass());
    }
}
