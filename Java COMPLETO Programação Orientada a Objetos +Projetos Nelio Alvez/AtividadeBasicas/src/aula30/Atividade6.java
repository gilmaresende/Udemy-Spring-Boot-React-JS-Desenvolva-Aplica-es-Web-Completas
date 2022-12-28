package aula30;

import java.util.Scanner;

public class Atividade6 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		/*
		 * Fazer um programa que leia tr�s valores com ponto flutuante de dupla
		 * precis�o: A, B e C. Em seguida, calcule e mostre:
		 */
		// a) a �rea do tri�ngulo ret�ngulo que tem A por base e C por altura.
		// b) a �rea do c�rculo de raio C. (pi = 3.14159)
		// c) a �rea do trap�zio que tem A e B por bases e C por altura.
		// d) a �rea do quadrado que tem lado B.
		// e) a �rea do ret�ngulo que tem lados A e B.

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
