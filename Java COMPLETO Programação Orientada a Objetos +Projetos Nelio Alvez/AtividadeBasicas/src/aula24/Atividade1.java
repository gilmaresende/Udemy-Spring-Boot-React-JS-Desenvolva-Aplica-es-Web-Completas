package aula24;

import java.util.Locale;

public class Atividade1 {

	public static void main(String[] args) {
		String product1 = "Computer";
		String product2 = "Office desk";
		int age = 30;
		int code = 5290;
		char gender = 'F';
		double price1 = 2100.0;
		double price2 = 650.50;
		double measure = 53.234567;

		/*
		 * Gerar Saidas: Products: Computer, which price is $ 2100,00 - ok Office desk,
		 * which price is $ 650,50 - ok Record: 30 years old, code 5290 and gender: F -
		 * ok Measue with eight decimal places: 53,23456700 Rouded (three decimal
		 * places): 53,235 US decimal point: 53.235
		 */

		System.out.printf("Products: %s, which price is $ %.2f \n", product1, price1);
		System.out.printf("%s, which price is $ %.2f\n", product2, price2);
		System.out.printf("Record: %d years old, code %d and gender: %s\n", age, code, gender);
		System.out.printf("Measue with eight  decimal places: %.8f\n", measure);
		System.out.printf("Rouded (three decimal places): %.3f\n", measure);
		Locale.setDefault(Locale.US);
		System.out.printf("US decimal point: %.3f\n", measure);
	}

}