package main;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

import model.Contrato;
import model.Parcela;
import services.impl.ContractService;
import services.impl.PaypalService;

public class Main {

	public static void main(String[] args) {

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		System.out.print("Entre com os dados do contrato:");
		System.out.println("Número:");
		int numeber = sc.nextInt();
		System.out.print("Data (dd/MM/yyyy):");
		LocalDate date = LocalDate.parse(sc.nextLine(), dtf);
		System.out.print("Valor do contrato:");
		Double value = sc.nextDouble();
		System.out.print("Entre com o número de parcelas");
		int qtd = sc.nextInt();
		
		ContractService c = new ContractService(new PaypalService());

		Contrato con = new Contrato(numeber, date, value);

		c.processContract(con, qtd);
		
		System.out.println("Parcelas");
		for (Parcela p : con.getParcelas()) {
			System.out.println(p);
		}

		sc.close();
	}

}
