package aula37;

import java.util.Scanner;

public class Atividade2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		/*
		 * Fazer um programa para ler um n�mero inteiro e dizer se este n�mero � par ou
		 * �mpar.
		 */
		int n = sc.nextInt();
		if (n % 2 == 0) {
			System.out.println("Par");
		} else {
			System.out.println("Impar");
		}

		sc.close();
	}

}
