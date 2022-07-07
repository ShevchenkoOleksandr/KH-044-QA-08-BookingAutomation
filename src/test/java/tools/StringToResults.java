package tools;

import java.time.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringToResults {

    public static void main(String[] args) {

        String dataIn = "2022-08-01";
        String dataOut = "2022-07-10";

        System.out.println("2022-07-10: "+monthToMills(dataOut));
        System.out.println("2022-07-10: "+daysToMills(dataOut));
        System.out.println("Kraków: 345 cars available "+stringToLocation("Kraków: 345 cars available"));
        System.out.println("Kraków: 345 cars available "+stringToResults("Kraków:  cars available"));
        System.out.println(stringToDate("Mon, 10 Oct 2022, 10:00"));
    }

    public static String monthToMills(String date){
        Month parseMonth = LocalDate.parse(date).getMonth();
        Integer parseYear = LocalDate.parse(date).getYear();

        long idInMills = LocalDate.of(parseYear, parseMonth.getValue(), 1)
                .atStartOfDay(ZoneOffset.UTC)
                .toInstant()
                .toEpochMilli();
        return "M"+idInMills;
    }
    public static String daysToMills (String date){
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
        if (m.find()) {
            temp = siteString.substring(m.start(), m.end());
            Months myMonth = Months.valueOf(temp.substring(3, 6).toUpperCase());
            return temp.substring(7) + "-" +myMonth.getNumOfMonth()+ "-" + temp.substring(0, 2);
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
        int result=0;
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
