package aula30;

import java.util.Scanner;

public class Atividade1 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		/*
		 * Atividade 1
		 * 
		 * Faça um programa para ler dois valores inteiros, e depois mostrar na tela a
		 * soma desses números com uma mensagem explicativa, conforme exemplos.
		 */

		int n1 = sc.nextInt();
		int n2 = sc.nextInt();
		int soma = n1 + n2;

		System.out.println("Soma = " + soma);

		sc.close();

	}

}
