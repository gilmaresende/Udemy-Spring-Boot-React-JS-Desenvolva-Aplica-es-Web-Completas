package aula37;

import java.util.Scanner;

public class Atividade3 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		/*
		 * Leia 2 valores inteiros (A e B). Ap�s, o programa deve mostrar uma mensagem
		 * "Sao Multiplos" ou "Nao sao Multiplos", indicando se os valores lidos s�o
		 * m�ltiplos entre si. Aten��o: os n�meros devem poder ser digitados em ordem
		 * crescente ou decrescente.
		 */

		int n1 = sc.nextInt();
		int n2 = sc.nextInt();
		if (n1 % n2 == 0 || n2 % n1 == 0) {
			System.out.println("S�o Multiplos");
		} else {
			System.out.println("N�o S�o Multiplos");
		}

		sc.close();
	}

}
