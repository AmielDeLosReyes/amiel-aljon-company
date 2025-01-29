package amiel_aljon.auth_server.config.logger;

import ch.qos.logback.classic.PatternLayout;
import ch.qos.logback.classic.spi.ILoggingEvent;
import lombok.Data;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Logback appender to mask a given pattern with a mask value.
 * Both pattern and mask value can be configured in the logback-spring.xml
 * E.g. Sensitive data like emails will be masked to * in the logs.
 */
@Data
public class MaskingPatternLayout extends PatternLayout {
    private static final String patternsProperty = "[>\\s\\t\\r\\n\\\"\\'\\{\\}-]([34567]{1}[0-9\\-\\s]{3})([0-9\\-\\s]{9,18})[<\\s\\t\\r\\n\\\"\\'\\{\\}-]|[>\\s\\t\\r\\n\\\"\\'\\{\\}-][A-Fa-f0-9]{32}[<\\s\\t\\r\\n\\\"\\'\\{\\}-]|[>\\s\\t\\r\\n\\\"\\'\\{\\}-][A-Fa-f0-9]{26}[<\\s\\t\\r\\n\\\"\\'\\{\\}-]|[>\\s\\t\\r\\n\\\"\\'\\{\\}-][A-Fa-f0-9]{38}[<\\s\\t\\r\\n\\\"\\'\\{\\}-]|[>\\s\\t\\r\\n\\\"\\'\\{\\}-][A-Fa-f0-9]{30}[<\\s\\t\\r\\n\\\"\\'\\{\\}-]|[>\\s\\t\\r\\n\\\"\\'\\{\\}-][A-Fa-f0-9]{34}[<\\s\\t\\r\\n\\\"\\'\\{\\}-]|[>\\s\\t\\r\\n\\\"\\'\\{\\}-][A-Fa-f0-9]{67}[<\\s\\t\\r\\n\\\"\\'\\{\\}-]|[>\\s\\t\\r\\n\\\"\\'\\{\\}-][A-Fa-f0-9]{41}[<\\s\\t\\r\\n\\\"\\'\\{\\}-]";
    private static final String replacementString = "XXXXXXXXXXXXXXXX";

    @Override
    public String doLayout(ILoggingEvent event) {
        String message = super.doLayout(event);
        Pattern pattern = Pattern.compile(patternsProperty);
        Matcher matcher = pattern.matcher(message);
        while (matcher.find()) {
            int groups = matcher.groupCount();
            for (int i = 0; i < groups; i++) {
                String matched = matcher.group(i);
                if (matched != null) {
                    message = message.replaceAll(matched, maskString(matched));
                }
            }
        }
        return message;
    }

    private String maskString(String input) {
        if (input != null && input.length() > 4) {
            String middleString = input.substring(4, input.length() - 4);
            StringBuilder replaceString = new StringBuilder();
            for (int i = 0; i < middleString.length(); i++) {
                replaceString.append("X");
            }
            input = input.replaceFirst(middleString, replaceString.toString());
        }
        return input;
    }
}
