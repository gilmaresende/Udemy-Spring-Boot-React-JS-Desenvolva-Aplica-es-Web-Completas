package aula30;

import java.util.Scanner;

public class Atividade3 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		/*
		 * Fazer um programa para ler quatro valores inteiros A, B, C e D. A seguir,
		 * calcule e mostre a diferen�a do produto de A e B pelo produto de C e D
		 * segundo a f�rmula: DIFERENCA = (A * B - C * D).
		 */

		int a = sc.nextInt();
		int b = sc.nextInt();
		int c = sc.nextInt();
		int d = sc.nextInt();
		int dif = (a * b) - (c * d);
		System.out.println("Diferen�a = " + dif);

		sc.close();
	}

}
