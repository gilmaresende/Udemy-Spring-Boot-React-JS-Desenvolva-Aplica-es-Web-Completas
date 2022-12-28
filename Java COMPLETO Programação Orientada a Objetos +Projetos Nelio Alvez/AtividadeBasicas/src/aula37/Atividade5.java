package aula37;

import java.util.Scanner;

public class Atividade5 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		/*
		 * Com base na tabela abaixo, escreva um programa que leia o código de um item e
		 * a quantidade deste item. A seguir, calcule e mostre o valor da conta a pagar.
		 */
		// cod - descicao - valor
		// 1 - Cachorro quente - 4,00
		// 2 - X Salada - 4,50
		// 3 - X Bacon - 5,00
		// 4 - Torrada simples - 2,00
		// 5 - refrigerante - 1,50

		int item = sc.nextInt();
		int qtd = sc.nextInt();
		double preco = 0d;
		if (item == 1) {
			preco = 4d;
		} else if (item == 2) {
			preco = 4.5;
		} else if (item == 3) {
			preco = 5d;
		} else if (item == 4) {
			preco = 2d;
		} else {
			preco = 1.5;
		}

		double valor = qtd * preco;

		System.out.printf("Total: %.2f", valor);

		sc.close();
	}

}
