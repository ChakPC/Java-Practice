package labs;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.ResourceBundle;

public class practice {
    public static void main (String[] args) {
        // String Internment
        String teaTxt = "Tea";
        String b = "Tea";
        System.out.println(teaTxt == b);   // Return true because both teaTxt and b reference the same String object on heap

        String c = new String("Tea");
        System.out.println(teaTxt == c);   // Return false because c references a newly created object on heap
        
        c.intern();
        System.out.println(teaTxt == c);   // Return false

        String d = c.intern();
        System.out.println(teaTxt == d);   // Return true because d points to the same object on heap pointed by teaTxt


        // StringBuilder - To create mutable strings (String creates immuable strings)
        StringBuilder txt = new StringBuilder(c);  // StringBuilder object must be created using new

        System.out.println(txt.capacity());
        System.out.println(txt.length());


        // BigDecimal 
        BigDecimal price = BigDecimal.valueOf(1.85);
        BigDecimal rate = BigDecimal.valueOf(0.065);
        price = price.subtract(price.multiply(rate).setScale(2, RoundingMode.HALF_UP));  // Rounding in decimal not explicitly provided with double primitive


        // Format values - Locale and Number Format
        Locale locale = Locale.FRANCE;  // (or) new Locale("fr", "FR"); (or) Locale.forLanguageTag("fr-FR");
        // Locale locale = Locale.UK;  // (or) new Locale("en", "GB"); (or) Locale.forLanguageTag("en-GB");
        // Locale locale = new Locale("ru", "RU");

        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(locale);
        NumberFormat percentFormat = NumberFormat.getPercentInstance(locale);
        percentFormat.setMaximumFractionDigits(2);

        String formatedPrice = currencyFormat.format(price);
        String formatedRate = percentFormat.format(rate);

        System.out.println(formatedPrice);
        System.out.println(formatedRate);


        // Use and Format Date and Time Values
        LocalDate today = LocalDate.now();
        System.out.println(today.plusYears(1).getDayOfWeek());

        LocalTime teaTime = LocalTime.of(17, 30);
        Duration timeGap = Duration.between(LocalTime.now(), teaTime);
        System.out.println(timeGap.toMinutes());
        System.out.println(timeGap.toHours());
        System.out.println(timeGap.toMinutesPart());

        LocalDateTime tomorrowTeaTime = LocalDateTime.of(today.plusDays(1), teaTime);
        System.out.println(tomorrowTeaTime);

        ZoneId london = ZoneId.of("Europe/London");
        ZoneId katmandu = ZoneId.of("Asia/Katmandu");

        ZonedDateTime londonTime = ZonedDateTime.of(tomorrowTeaTime, london);
        ZonedDateTime katmanduTime = londonTime.withZoneSameInstant(katmandu);
        System.out.println(londonTime);
        System.out.println(katmanduTime);
        System.out.println(katmanduTime.getOffset());

        String dataPattern = "EE', 'd' of 'MMMM yyyy' at 'HH:mm z";
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern(dataPattern, locale);
        String timeTxt = dateFormat.format(katmanduTime);
        System.out.println(timeTxt);


        // ResourceBundle
        ResourceBundle msg = ResourceBundle.getBundle("messages", locale);
        String offerPattern = msg.getString("offer");
        String datePattern = msg.getString("dateFormat");
        dateFormat = DateTimeFormatter.ofPattern(datePattern, locale);
        timeTxt = dateFormat.format(katmanduTime);
        String offerMsg = MessageFormat.format(offerPattern, teaTxt, formatedPrice, formatedRate, timeTxt);
        System.out.println(offerMsg);
    }
}
