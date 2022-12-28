package aula30;

import java.util.Scanner;

public class Atividade6 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		/*
		 * Fazer um programa que leia três valores com ponto flutuante de dupla
		 * precisão: A, B e C. Em seguida, calcule e mostre:
		 */
		// a) a área do triângulo retângulo que tem A por base e C por altura.
		// b) a área do círculo de raio C. (pi = 3.14159)
		// c) a área do trapézio que tem A e B por bases e C por altura.
		// d) a área do quadrado que tem lado B.
		// e) a área do retângulo que tem lados A e B.

		double pi = 3.14159;
		double a = sc.nextDouble();
		double b = sc.nextDouble();
		double c = sc.nextDouble();

		double triangulo = (a * c) / 2;
		double circulo = pi * Math.pow(c, 2);
		double trapezio = ((a + b) / 2) * c;
		double quadrado = b * b;
		double retangulo = a * b;

		System.out.printf("TRIANGULO %.2f\n", triangulo);
		System.out.printf("CIRCULO %.2f\n", circulo);
		System.out.printf("TRAPEZIO %.2f\n", trapezio);
		System.out.printf("QUADRADO %.2f\n", quadrado);
		System.out.printf("RETANGULO %.2f\n", retangulo);
		sc.close();
	}

}
