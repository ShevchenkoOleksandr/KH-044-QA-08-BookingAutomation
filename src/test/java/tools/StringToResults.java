package tools;

import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneOffset;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringToResults {

    public static String monthToMills(String date) {
        Month parseMonth = LocalDate.parse(date).getMonth();
        int parseYear = LocalDate.parse(date).getYear();

        long idInMills = LocalDate.of(parseYear, parseMonth.getValue(), 1)
                .atStartOfDay(ZoneOffset.UTC)
                .toInstant()
                .toEpochMilli();
        return "M" + idInMills;
    }

    public static String daysToMills(String date) {
        long idInMills = LocalDate.parse(date)
                .atStartOfDay(ZoneOffset.UTC)
                .toInstant()
                .toEpochMilli();
        return String.valueOf(idInMills);
    }

    public static String stringToDate(String siteString) {
        String pattern = "\\d{1,2} \\w{3} \\d{4}";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(siteString);
        String temp;
        int days;
        String day;
        if (m.find()) {
            temp = siteString.substring(m.start(), m.end());
            if (temp.length() == 11) {
                days = 2;
                day = temp.substring(0, days);

            } else {
                days = 1;
                day = "0"+temp.substring(0, days);
            }
            Months myMonth = Months.valueOf(temp.substring(days + 1, days + 4).toUpperCase());
            return temp.substring(temp.length() - 4) + "-" + myMonth.getNumOfMonth() + "-" + day;
        }
        return "";
    }

    public static String stringToLocation(String siteString) {
        for (int i = 0; i < siteString.length(); i++) {
            if (siteString.charAt(i) == ':') {
                return siteString.substring(0, i);
            }
        }
        return "";
    }

    public static int stringToResults(String siteString) {
        StringBuilder str = new StringBuilder();
        int result = 0;
        char temp;
        for (int i = 0; i < siteString.length(); i++) {
            temp = siteString.charAt(i);
            if (Character.isDigit(temp)) {
                str.append(temp);
            }
        }
        if (!str.isEmpty()) result = Integer.parseInt(String.valueOf(str));
        return result;
    }
}
