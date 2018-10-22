import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;


public class Java8ZonedDateTimeTest {

	public static void main(String[] args) {
		System.out.println("System Default Zone Id: " + ZoneId.systemDefault());
		
		/*Set<String> availableZoneIds = ZoneId.getAvailableZoneIds();
		availableZoneIds.forEach(System.out::println);*/
		
		ZoneId zoneId1 =  ZoneId.of("Asia/Kolkata");
		ZoneId zoneId2 =  ZoneId.of("Africa/Cairo");
		
		LocalDateTime localDateTime = LocalDateTime.now();
		System.out.println(ZonedDateTime.of(localDateTime, zoneId1));
		System.out.println(ZonedDateTime.of(localDateTime, zoneId2));
		
		ZoneOffset zoneOffset1 = ZoneOffset.of("-02:00");
		ZoneOffset zoneOffset2 = ZoneOffset.of("+10:45");
		
		System.out.println(OffsetDateTime.of(localDateTime, zoneOffset1));
		System.out.println(OffsetDateTime.of(localDateTime, zoneOffset2));
		
	}

}
