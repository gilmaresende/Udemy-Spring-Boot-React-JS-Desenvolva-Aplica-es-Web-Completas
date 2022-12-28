package aula30;

import java.util.Scanner;

public class Atividade5 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		/*
		 * Fazer um programa para ler o código de uma peça 1, o número de peças 1, o
		 * valor unitário de cada peça 1, o código de uma peça 2, o número de peças 2 e
		 * o valor unitário de cada peça 2. Calcule e mostre o valor a ser pago.
		 */

		int cod1 = sc.nextInt();
		int qtd1 = sc.nextInt();
		double valor1 = sc.nextDouble();
		int cod2 = sc.nextInt();
		int qtd2 = sc.nextInt();
		double valor2 = sc.nextDouble();
		double vlr = (qtd1 * valor1) + (qtd2 * valor2);
		System.out.printf("Valor a pagar = R$ %.2f\n", vlr);
		sc.close();
	}

}
