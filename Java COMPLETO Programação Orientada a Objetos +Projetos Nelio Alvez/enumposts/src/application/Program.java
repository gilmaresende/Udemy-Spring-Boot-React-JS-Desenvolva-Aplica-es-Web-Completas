package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import entities.Coment;
import entities.Post;

public class Program {

	public static void main(String[] args) throws ParseException {

		Coment c1 = new Coment("Have a nice trip!");
		Coment c2 = new Coment("Wow that's awesome!");

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

		Post p1 = new Post(sdf.parse("21/06/2018 13:05:44"), "Traveling to New Zealand",
				"I'm going to visit this wonderfull country!", 12);
		
		p1.addComment(c1);
		p1.addComment(c2);
		
		System.out.println(p1);
	}

}
