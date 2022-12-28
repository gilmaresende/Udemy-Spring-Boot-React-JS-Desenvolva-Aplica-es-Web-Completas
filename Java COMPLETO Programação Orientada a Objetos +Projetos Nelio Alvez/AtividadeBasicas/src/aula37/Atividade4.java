package aula37;

import java.util.Scanner;

public class Atividade4 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		/*
		 * Leia a hora inicial e a hora final de um jogo. A seguir calcule a dura��o do
		 * jogo, sabendo que o mesmo pode come�ar em um dia e terminar em outro, tendo
		 * uma dura��o m�nima de 1 hora e m�xima de 24 horas.
		 */
		int hInicial = sc.nextInt();
		int hFinal = sc.nextInt();
		if (hInicial > hFinal) {
			hFinal = hFinal + 24;
		}
		int tempo = hFinal - hInicial;

		System.out.printf("O JOGO DUROU %d HORA(S)\n", tempo);

		sc.close();
	}

}
