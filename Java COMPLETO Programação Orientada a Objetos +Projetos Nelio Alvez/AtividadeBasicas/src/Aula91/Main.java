package Aula91;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner sc = new Scanner(System.in);
		Estudante[] quatros = new Estudante[10];
		int qtdEstudantes = sc.nextInt();
		
		for(int i = 0; i<qtdEstudantes;i++) {
			sc.nextLine();
			String nome = sc.nextLine();
			String email = sc.nextLine();
			
			Estudante e = new Estudante(nome, email);
			int quanto = sc.nextInt();
			quatros[quanto] = e;
		}
		
		sc.close();
		
		for(int i = 0; i< quatros.length; i++) {
			if(quatros[i]!=null) {
				System.out.println(i + " " + quatros[i].getNome() + " - " + quatros[i].getEmail());
			}
		}

	}

}
