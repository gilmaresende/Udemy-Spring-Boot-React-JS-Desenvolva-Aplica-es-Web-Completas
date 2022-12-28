package aula30;

import java.util.Scanner;

public class Atividade2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		/*
		 * Faça um programa para ler o valor do raio de um círculo, e depois mostrar o
		 * valor da área deste círculo com quatro casas decimais conforme exemplos.
		 * Fórmula da área: area = π . raio2 Considere o valor de π = 3.14159
		 */

		double pi = 3.14159;

		double r = sc.nextDouble();

		double area = pi * Math.pow(r, 2);

		System.out.println("A = " + area);

		sc.close();

	}

}
