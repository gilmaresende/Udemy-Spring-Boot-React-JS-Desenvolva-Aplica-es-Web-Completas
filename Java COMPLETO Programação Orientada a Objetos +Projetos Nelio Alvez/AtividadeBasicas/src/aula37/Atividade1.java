package aula37;

import java.util.Scanner;

public class Atividade1 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		/*
		 * Fazer um programa para ler um número inteiro, e depois dizer se este número é
		 * negativo ou não.
		 */
		int n = sc.nextInt();
		if (n < 0) {
			System.out.println("Negativo");
		} else {
			System.out.println("Não Negativo");
		}
		sc.close();
	}

}
