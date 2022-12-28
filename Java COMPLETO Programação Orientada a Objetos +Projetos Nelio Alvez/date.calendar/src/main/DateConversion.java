package main;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class DateConversion {

	public static void main(String[] args) {
		
		Instant dataGlobal = Instant.parse("2022-07-20T02:02:40Z");
		
	
		LocalDate d04 = LocalDate.parse("2022-07-20");
		
		System.out.println("d4 Dia: " + d04.getDayOfMonth());
		System.out.println("d4 Mês: " + d04.getMonthValue());
		System.out.println("d4 Ano: " + d04.getYear());


		LocalDateTime l5a = LocalDateTime.ofInstant(dataGlobal, ZoneId.systemDefault());
		LocalDateTime l5b = LocalDateTime.ofInstant(dataGlobal, ZoneId.of("Portugal"));
		System.out.println(l5a);
		System.out.println(l5b);
		
		System.out.println("d5 Hora: " + l5a.getHour());
		System.out.println("d5 Minuto: " + l5a.getMinute());
	
	

		LocalDate l6a = LocalDate.ofInstant(dataGlobal, ZoneId.systemDefault());
		LocalDate l6b = LocalDate.ofInstant(dataGlobal, ZoneId.of("Portugal"));
		System.out.println(l6a);
		System.out.println(l6b);
		
	}

}
