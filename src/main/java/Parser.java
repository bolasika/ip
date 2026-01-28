import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Utility class for validating user inputs and outputs
 */
public class Parser {
    /* Regex pattern for date-time in DD/MM/YYYY with 24-hour time */
    private static final String REGEX_SLASH_DMY = "\\d{1,2}/\\d{1,2}/\\d{4}\\s\\d{4}";

    /* Regex pattern for date-time in YY-MM-DD with 24-hour time */
    private static final String REGEX_DASH_YMD = "\\d{4}-\\d{2}-\\d{1,2}\\s\\d{4}";

    /**
     * Check whether the provided input matches a supported date-time format
     * If matches, it would be parsed as {@link LocalDateTime} and
     * return a display format: {@code "MMM dd uuuu h.mma"}.
     * If the input does not match any support format, return the original input
     *
     * @param input The user input data that was stored in the Task
     * @return A normalized date-time string if matches the regex, else original input.
     */
    public static String dateChecker(String input) {
        if (input.matches(REGEX_SLASH_DMY) || input.matches(REGEX_DASH_YMD)) {
            DateTimeFormatter dateFmter;
            if (input.matches(REGEX_SLASH_DMY)) {
                dateFmter = DateTimeFormatter.ofPattern("d/M/uuuu HHmm");
            } else {
                dateFmter = DateTimeFormatter.ofPattern("uuuu-MM-d HHmm");
            }
            LocalDateTime date = LocalDateTime.parse(input, dateFmter);
            return date.format(DateTimeFormatter.ofPattern("MMM dd uuuu h.mma", Locale.ENGLISH));

        }
        return input;
    }
}
