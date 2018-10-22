import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/* Represents combination of Date and Time */
public class Java8LocalDateTimeTest {

	public static void main(String[] args) {

		System.out.println(LocalDateTime.now());
		System.out.println(LocalDateTime.of(LocalDate.now(), LocalTime.now()));
		System.out.println(LocalDateTime.parse("1980-06-18T04:30:33.533"));
		System.out.println(LocalDateTime.parse("1980-06-18T04:30:33.533").toLocalDate());
		System.out.println(LocalDateTime.parse("1980-06-18T04:30:33.533").toLocalTime());
		System.out.println(LocalDateTime.parse("1980-06-18T04:30:33.533").range(ChronoField.HOUR_OF_DAY)); // returns Type ValueRange - Gets the range of valid values for the specified field. 
		
		System.out.println(LocalDateTime.parse("1980-06-18T04:30:33.533").truncatedTo(ChronoUnit.HOURS));
		
		System.out.println(LocalDateTime.parse("1980-06-18T04:30:33.533").atZone(ZoneId.of("Asia/Kolkata"))); // Combines this date-time with a time-zone to create a ZonedDateTime.
		System.out.println(LocalDateTime.parse("1980-06-18T04:30:33.533").atOffset(ZoneOffset.UTC)); // Combines this date-time with an offset to create an OffsetDateTime.
		
		
		/* The Instant class in the Java date time API (java.time.Instant) represents a specific moment on the time line. 
		 * The instant is defined as an offset since the origin (called an epoch). The origin is Jan. 1st 1970 - 00:00 - Greenwhich mean time (GMT).
		   Time is measured using 86.400 seconds per day, moving forward from the origin. 
		   An Instant object contains two fields internally which holds the time represented by the Instant: 	a) Seconds since the epoch. b) Nanoseconds
		*/
		
		Instant now = Instant.now();
		System.out.println(now);
		System.out.println(now.getEpochSecond());
		System.out.println(now.getNano());
		
		System.out.println(LocalDateTime.now().toInstant(ZoneOffset.UTC)); // Converts this date-time to an Instant. 
		// Utility APIs like getMonth, plusDays, minusHours, MAX, MIN etc. behave in same way as LocalDate and LocalTime
		
		// Compatibility with old Java Date-time and Caleder APIs
		
		Date date = new Date(); // old API
		//LocalDateTime localDateTime1 = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()); // converted to New API // Gets time in CST
		LocalDateTime localDateTime1 = LocalDateTime.ofInstant(date.toInstant(), ZoneId.of("Asia/Kolkata")); // Gets Indian time (in IST)
		System.out.println(localDateTime1);		
		System.out.println(localDateTime1.toLocalDate());
		System.out.println(localDateTime1.toLocalTime());	
		
		Calendar cal = Calendar.getInstance(); // old API
		// Obtains an instance of LocalDateTime from an Instant and zone ID.
		LocalDateTime localDateTime2 = LocalDateTime.ofInstant(cal.toInstant(), ZoneId.systemDefault()); // converted to New API
		System.out.println(localDateTime2);		
		System.out.println(localDateTime2.toLocalDate());
		System.out.println(localDateTime2.toLocalTime());
		
		//formatting
		
		System.out.println(localDateTime2.format(DateTimeFormatter.ISO_DATE_TIME));
		System.out.println(localDateTime2.format(DateTimeFormatter.ISO_DATE));
		System.out.println(localDateTime2.format(DateTimeFormatter.ISO_TIME));
		System.out.println(localDateTime2.format(DateTimeFormatter.BASIC_ISO_DATE));
		
		System.out.println(localDateTime2.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL, FormatStyle.SHORT).withLocale(Locale.ENGLISH)));
		System.out.println(localDateTime2.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL, FormatStyle.SHORT).withLocale(Locale.UK)));
		
	}

}
