package main;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.TimeZone;

public class DateLegado {
	public static void main(String[] args) throws ParseException {

		SimpleDateFormat s1 = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat s2 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		SimpleDateFormat s3 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		s3.setTimeZone(TimeZone.getTimeZone("GMT"));

		Date y1 = s1.parse("25/06/2022");
		Date y2 = s1.parse("25/06/2018 15:42:07");
		Date y3 = Date.from(Instant.parse("2018-06-25T15:42:07Z"));
		
		Date x1 = new Date();
		Date x2 = new Date(System.currentTimeMillis());
		Date x3 = new Date(0);
		Date x4 = new Date(5*1000*60*60);

	
		System.out.println("----------------------------");

		System.out.println("x1: " + s2.format(x1));
		System.out.println("x2: " + s2.format(x2));
		System.out.println("x3: " + s2.format(x3));
		System.out.println("x4: " + s2.format(x4));
		System.out.println("y1: " + s2.format(y1));
		System.out.println("y2: " + s2.format(y2));
		System.out.println("y3: " + s2.format(y3));
		
		System.out.println("----------------------------");
		
		System.out.println("x1: " + s3.format(x1));
		System.out.println("x2: " + s3.format(x2));
		System.out.println("x3: " + s3.format(x3));
		System.out.println("x4: " + s3.format(x4));
		System.out.println("y1: " + s3.format(y1));
		System.out.println("y2: " + s3.format(y2));
		System.out.println("y3: " + s3.format(y3));
		
	}

}
