package main;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateLocalDate {

	public static void main(String[] args) {
		LocalDate d01 = LocalDate.now();

		System.out.println(d01);

		LocalDateTime d02 = LocalDateTime.now();

		System.out.println(d02);

		Instant d03 = Instant.now();

		System.out.println(d03);

		LocalDate d04 = LocalDate.parse("2022-07-20");

		System.out.println(d04);

		LocalDateTime d05 = LocalDateTime.parse("2022-07-20T02:02:40");

		System.out.println(d05);

		Instant d06 = Instant.parse("2022-07-20T02:02:40Z");

		System.out.println(d06);

		Instant d07 = Instant.parse("2022-07-20T02:02:40-03:00");

		System.out.println(d07);

		/* Formatar */

		DateTimeFormatter f8 = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		LocalDate d08 = LocalDate.parse("20/07/2022", f8);

		System.out.println(d08);
		
		DateTimeFormatter f9 = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

		LocalDateTime d09 = LocalDateTime.parse("20/07/2022 01:30" , f9);

		System.out.println(d09);
		
		LocalDate d10 = LocalDate.of(2022,7,20);
		System.out.println(d10);
		
		LocalDateTime d11 = LocalDateTime.of(2022,7,20, 10,02);
		System.out.println(d11);

	}

}
