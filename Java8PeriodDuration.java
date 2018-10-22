import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;


public class Java8PeriodDuration {

	/* Period represents quantity of time in years,months and days */
	/* Duration represents quantity of time in seconds and nano-seconds */
	/* Period and Duration are implementations of TemporalAmount and hence generally used where TemporalAmount is expected */
	
	public static void main(String[] args) {
		LocalDateTime dateTime = LocalDateTime.now();
		System.out.println(dateTime.plus(Period.ofDays(3)));
		System.out.println(dateTime.plus(Duration.ofHours(5)));
		
		System.out.println(Period.between(LocalDate.now(), LocalDate.MAX).get(ChronoUnit.YEARS));
		System.out.println(ChronoUnit.YEARS.between(LocalDate.now(), LocalDate.MAX)); // Alternate way to obtain difference
		
		System.out.println(Duration.between(LocalTime.now(), LocalTime.MAX).get(ChronoUnit.SECONDS));
		System.out.println(ChronoUnit.SECONDS.between(LocalTime.now(), LocalTime.MAX));
		
	}
}
