import java.time.Clock;
import java.time.Duration;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;

 /* Time without a Date */ 

public class Java8LocalTimeTest {

	public static void main(String[] args) {
		System.out.println(LocalTime.now());
		System.out.println(LocalTime.now(Clock.systemDefaultZone()));
		System.out.println(LocalTime.now(ZoneId.of("Asia/Kolkata")));
		
		System.out.println(LocalTime.of(22, 22, 22));
		System.out.println(LocalTime.of(22, 22, 22).plus(Duration.ofHours(3))); //The amount is typically Duration but may be any other type implementing the TemporalAmount interface. 
		
		System.out.println(LocalTime.parse("23:55:45"));
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("kk-mm-ss");
		LocalTime localTime1 = LocalTime.parse("20-55-47", formatter);
		System.out.println(localTime1);
		System.out.println(formatter.format(localTime1));
		
		//System.out.println(LocalTime.parse("6:30").isSupported(ChronoField.ALIGNED_WEEK_OF_MONTH));  // Beware 6:30 will not work. Has to be 06:30 
		System.out.println(LocalTime.parse("06:30").isSupported(ChronoField.ALIGNED_WEEK_OF_MONTH));  //returns false //Checks if the specified field is supported. 
		System.out.println(LocalTime.parse("06:30").isSupported(ChronoField.CLOCK_HOUR_OF_DAY)); //returns true
		
		System.out.println(LocalTime.parse("06:30:34.33").getNano());
		System.out.println(LocalTime.parse("06:30:34.33").getHour());
		System.out.println(LocalTime.parse("06:30:34.33").isSupported(ChronoUnit.CENTURIES));
		System.out.println(LocalTime.parse("06:30:34.33").isSupported(ChronoUnit.MICROS));
		
		System.out.println(LocalTime.MAX);
		System.out.println(LocalTime.MIN);
		System.out.println(LocalTime.MIDNIGHT);
		
	}

}
