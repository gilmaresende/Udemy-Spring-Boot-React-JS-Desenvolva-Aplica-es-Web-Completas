package map;

import java.util.Map;
import java.util.TreeMap;

public class Demo {

	public static void main(String[] args) {
		Map<String, String> cookins = new TreeMap<>();
		cookins.put("username", "Maria");
		cookins.put("email", "maria@gmail.com");
		cookins.put("phone", "99711122");

		cookins.remove("email");
		cookins.put("phone", "1133");

		System.out.println("Contains 'phone' key: " + cookins.containsKey("phone"));
		System.out.println("Phone number: " + cookins.get("phone"));
		System.out.println("Email: " + cookins.get("email"));
		System.out.println("Size: " +  cookins.size());
		
		System.out.println("All Cookins");
		for (String key : cookins.keySet()) {
			System.out.println(key + ": " + cookins.get(key));
		}
	}

}
