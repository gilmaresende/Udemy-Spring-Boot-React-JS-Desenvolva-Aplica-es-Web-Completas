package aula37;

import java.util.Scanner;

public class Atividade1 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		/*
		 * Fazer um programa para ler um n�mero inteiro, e depois dizer se este n�mero �
		 * negativo ou n�o.
		 */
		int n = sc.nextInt();
		if (n < 0) {
			System.out.println("Negativo");
		} else {
			System.out.println("N�o Negativo");
		}
		sc.close();
	}

}
