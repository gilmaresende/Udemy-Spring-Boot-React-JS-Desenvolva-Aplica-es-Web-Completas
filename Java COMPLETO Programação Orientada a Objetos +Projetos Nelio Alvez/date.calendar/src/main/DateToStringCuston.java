package main;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class DateToStringCuston {

	public static void main(String[] args) {

		LocalDate d04 = LocalDate.parse("2022-07-20");
		DateTimeFormatter f4 = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		System.out.println(d04.format(f4));
		System.out.println(f4.format(d04));
		

		LocalDateTime d05 = LocalDateTime.parse("2022-07-20T02:02:40");
		DateTimeFormatter f5 = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		System.out.println(d05.format(f5));
		System.out.println(f5.format(d05));
		DateTimeFormatter f5_1 = DateTimeFormatter.ISO_DATE_TIME;
		System.out.println(f5_1.format(d05));

		Instant d06 = Instant.parse("2022-07-20T02:02:40Z");
		DateTimeFormatter f6 = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm").withZone(ZoneId.systemDefault());
		System.out.println(f6.format(d06));
		DateTimeFormatter f6_1 = DateTimeFormatter.ISO_INSTANT;
		System.out.println(f6_1.format(d06));
		
	}

}
