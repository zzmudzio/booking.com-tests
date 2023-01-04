package pl.zzmudzio.tools;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* this class is responsible for executing some regular expressions to retrieve needed information */

public class RegEx {
    public static String getDayOfMonthFromGivenString(String dateToExtract) {
        Pattern pattern = Pattern.compile("(^[a-ząęźćńóś.\\s]+)([0-9]+)(.*)");
        Matcher matcher = pattern.matcher(dateToExtract);
        if(matcher.find()) {
            return matcher.group(2);
        }
        else {
            return null;
        }
    }
}
