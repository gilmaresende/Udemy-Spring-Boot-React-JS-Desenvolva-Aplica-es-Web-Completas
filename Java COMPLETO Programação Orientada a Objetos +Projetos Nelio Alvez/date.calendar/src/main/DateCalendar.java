package main;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;

public class DateCalendar {

	public static void main(String[] args) {

		SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

		Date d = Date.from(Instant.parse("2018-06-25T15:42:07Z"));
		System.out.println(s.format(d));

		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		cal.add(Calendar.HOUR_OF_DAY, 4);
		d = cal.getTime();
		System.out.println(s.format(d));

		/* get unidade de tempo */

		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(d);
		int day = cal2.get(Calendar.DAY_OF_MONTH);

		System.out.println("day: " + day);

		int minutes = cal2.get(Calendar.MINUTE);

		System.out.println("minutes: " + minutes);

		int mes = cal2.get(Calendar.MONTH) + 1;

		System.out.println("mes: " + mes);

	}
}
