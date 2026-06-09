package fa.training.util;

import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

public class Constant {
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter
            .ofPattern("uuuu-MM-dd")
            .withResolverStyle(ResolverStyle.STRICT);
}
