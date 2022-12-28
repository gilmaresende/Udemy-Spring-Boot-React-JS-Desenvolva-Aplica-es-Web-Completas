package program;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Excep1 {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		try {
			String[] vect = sc.nextLine().split(" ");
			int position = sc.nextInt();

			System.out.println(vect[position]);

		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Posicao invalida");
		} catch (InputMismatchException e) {
			System.out.println("Entrada invalida");
		}

		sc.close();

		System.out.println("Fim do Programa");

	}

	public static void main1(String[] args) {
		Scanner sc = new Scanner(System.in);
		try {
			String[] vect = sc.nextLine().split(" ");
			int position = sc.nextInt();

			System.out.println(vect[position]);

		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Posicao invalida");
		} catch (InputMismatchException e) {
			System.out.println("Entrada invalida");
		}

		sc.close();

		System.out.println("Fim do Programa");

	}

}
