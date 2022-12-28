package Aula95;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Program {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<String> list = new ArrayList<>();

		list.add("Maria");
		list.add("Alex");
		list.add("Bob");
		list.add("Ana");
		list.add(2, "Marco");

		System.out.println(list.size());

		for (String s : list) {
			System.out.println(s);
		}

		System.out.println("-----------");
		// list.remove("Ana");
		// list.remove(1);
		list.removeIf(x -> x.charAt(0) == 'M');

		for (String s : list) {
			System.out.println(s);
		}

		System.out.println("Index of Bob: " + list.indexOf("Bob"));
		System.out.println("Index of Marco: " + list.indexOf("Marco"));
		System.out.println("-----------");

		List<String> result = list.stream().filter(x -> x.charAt(0) == 'A').collect(Collectors.toList());

		for (String s : result) {
			System.out.println(s);
		}
		System.out.println("-----------");

		String name = list.stream().filter(x -> x.charAt(0) == 'A').findFirst().orElse(null);
		System.out.println(name);
		
		 name = list.stream().filter(x -> x.charAt(0) == 'j').findFirst().orElse(null);
		System.out.println(name);
	}

}
