package aula37;

import java.util.Scanner;

public class Atividade6 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		/*
		 * Você deve fazer um programa que leia um valor qualquer e apresente uma
		 * mensagem dizendo em qual dos seguintes intervalos ([0,25], (25,50], (50,75],
		 * (75,100]) este valor se encontra. Obviamente se o valor não estiver em nenhum
		 * destes intervalos, deverá ser impressa a mensagem “Fora de intervalo”.
		 */

		double n = sc.nextDouble();
		if (n >= 0 && n <= 25) {
			System.out.println("Intervalo [0,25]");
		} else if (n >= 26 && n <= 50) {
			System.out.println("Intervalo [26,50]");
		} else if (n >= 51 && n <= 75) {
			System.out.println("Intervalo [51,75]");
		} else if (n >= 76 && n <= 100) {
			System.out.println("Intervalo [76,100]");
		} else {
			System.out.println("Fora de intervalo");
		}

		sc.close();
	}

}
