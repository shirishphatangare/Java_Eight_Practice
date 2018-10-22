import java.time.Clock;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;

/*New Date-Time API is immutable and Thread safe
	Java 8 under the package java.time introduced a new date-time API, most important classes among them are :
		Local : Simplified date-time API with no complexity of timezone handling.
		Zoned : Specialized date-time API to deal with various timezones. */	

public class Java8LocalDateTest {

	public static void main(String[] args) {
		// LocalDate class is immutable and thread-safe. LocalDate represents a date, often viewed as year-month-day.
		
		LocalDate localDate1 = LocalDate.now(); // Obtains the current date from the system clock in the default time-zone. 
		// This will query the specified clock to obtain the current date - today. Using this method allows the use of an alternate clock for testing.
		LocalDate localDate2 = LocalDate.now(Clock.systemDefaultZone()); // Obtains a clock that returns the current instant using the best available system clock, converting to date and time using the default time-zone. 
		LocalDate localDate3 = LocalDate.now(Clock.offset(Clock.systemDefaultZone(), Duration.ofDays(2)));  // Obtains a clock that returns instants from the specified clock with the specified duration added
		LocalDate localDate4 = LocalDate.now(ZoneId.of("Europe/Paris"));
		//LocalDate localDate4 = LocalDate.now(ZoneId.of(ZoneId.SHORT_IDS.get("IST"))); // Obtains the current date from the system clock in the specified time-zone. 
		
		System.out.println(localDate1);
		System.out.println(localDate2);
		System.out.println(localDate3);
		System.out.println(localDate4);
		
		LocalDate localDate5 = LocalDate.of(1980, 6, 18);
		System.out.println(localDate5);
		
		LocalDate localDate6 = LocalDate.ofEpochDay(1); // Obtains an instance of LocalDate from the epoch day count. This returns a LocalDate with the specified epoch-day. The EPOCH_DAY is a simple incrementing count of days where day 0 is 1970-01-01. Negative numbers represent earlier days.
		System.out.println(localDate6);
		
		LocalDate localDate7 = LocalDate.parse("2007-12-03"); //Obtains an instance of LocalDate from a text string such as 2007-12-03.
		System.out.println(localDate7);
		
		System.out.println(localDate7.plusDays(2));
		System.out.println(localDate7.minus(3, ChronoUnit.MONTHS)); // public enum ChronoUnit implements TemporalUnit 
		System.out.println(localDate7.minus(Period.ofYears(3))); // The amount is typically Period but may be any other type implementing the TemporalAmount interface. 
		System.out.println("Day of Year: " + localDate7.getDayOfYear());
		System.out.println("Day of Month: " + localDate7.get(ChronoField.DAY_OF_MONTH)); //  public enum ChronoField implements TemporalField 
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd"); // This formatting should match to text , otherwise DateTimeParseException - if the text cannot be parsed
		LocalDate localDate8 = LocalDate.parse("2007/12/03", formatter); // Obtains an instance of LocalDate from a text string using a specific formatter.
		System.out.println(localDate8); //default, print ISO_LOCAL_DATE
		System.out.println(formatter.format(localDate8)); // actual formatting

		/* The static method LocalDate.from(TemporalAccessor temporal) returns the LocalDate instance based on the provided TemporalAccessor.
		It attempts to extract the EPOCH_DAY field from the provided temporal accessor. If the provided temporal accessor doesn't support the EPOCH_DAY field DateTimeException is thrown. */
		
		System.out.println(LocalDate.from(LocalDateTime.now())); // public final class LocalDateTime implements Temporal, TemporalAdjuster, ChronoLocalDateTime<LocalDate>, Serializable
		System.out.println(LocalDate.from(ZonedDateTime.now())); // public final class ZonedDateTime implements Temporal, ChronoZonedDateTime<LocalDate>, Serializable
		System.out.println(LocalDate.from(OffsetDateTime.now())); // public final class OffsetDateTime implements Temporal, ChronoZonedDateTime<LocalDate>, Serializable
		
		// Leap Year
		System.out.println("Is this Leap Year? " + localDate8.isLeapYear());
		System.out.println("Is previous year Leap Year? " + localDate8.minusYears(1).isLeapYear());
		System.out.println("Is next year Leap Year? " + localDate8.plusYears(1).isLeapYear());
		
		System.out.println("IsBefore: " +  LocalDate.of(2018, 10, 18).isBefore(LocalDate.parse("2018-10-23")));
		System.out.println("IsAfter: " +  LocalDate.of(2018, 10, 18).isAfter(LocalDate.parse("2018-10-12")));
		System.out.println("StartOfDay: " +  LocalDate.of(2018, 10, 18).atStartOfDay());
		System.out.println("StartOfDay: " +  LocalDate.of(2018, 10, 18).atStartOfDay(ZoneId.of("Asia/Kolkata")));
		
		System.out.println("with1: " +  LocalDate.of(2018, 10, 18).withYear(2007)); // Change 2018 to 2007 
		System.out.println("with2: " +  LocalDate.of(2018, 10, 18).with(ChronoField.DAY_OF_MONTH, 12)); // Change 18 to 12
		System.out.println("with3: " +  LocalDate.of(2018, 10, 18).with(TemporalAdjusters.firstDayOfNextMonth())); //  TemporalAdjusters class provides Common and useful TemporalAdjuster
		
		System.out.println("Max Date: " + LocalDate.parse("2018-10-18").MAX );
		System.out.println("Min Date: " + LocalDate.parse("2018-10-18").MIN);
	}
}
