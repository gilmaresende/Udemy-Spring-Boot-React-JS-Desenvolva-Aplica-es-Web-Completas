package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import entities.Department;
import entities.HourContract;
import entities.Worker;
import entities.enums.WorkerLevel;

public class Main {

	public static void main(String[] args) throws ParseException {

		SimpleDateFormat fDt = new SimpleDateFormat("dd/MM/yyyy");

		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);

		System.out.print("Entre com nome do Departamento:");
		String departmentName = sc.nextLine();

		System.out.print("Nome do colaborador:");
		String workerName = sc.nextLine();

		System.out.print("Nivel do colaborador:");
		String workerLeval = sc.nextLine();

		System.out.print("Salario do colaborador:");
		double workerSalary = sc.nextDouble();

		Worker worker = new Worker(workerName, WorkerLevel.valueOf(workerLeval), workerSalary,
				new Department(departmentName));

		System.out.print("Quantidade de Contratos:");
		Integer nContract = sc.nextInt();

		for (int i = 0; i < nContract; i++) {

			System.out.println("Contract Nº: " + i + 1);

			System.out.println("DD/MM/YYYY");
			
			Date dt = fDt.parse(sc.next());

			System.out.println("Valor Hour");
			double value = sc.nextDouble();

			System.out.print("Quantidade Horas:");
			Integer qtd = sc.nextInt();

			worker.addContract(new HourContract(dt, value, qtd));
		}

		System.out.println("Entre com mes e ano para calcular salario: (MM/YYYY)");
		String monthAndYear = sc.next();

		int month = Integer.parseInt(monthAndYear.substring(0, 2));

		int year = Integer.parseInt(monthAndYear.substring(3));

		System.out.println("Name: " + worker.getName());

		System.out.println("Department: " + worker.getDepartment().getName());

		System.out.println("Income for : " + monthAndYear + ": " + String.format("%.2f", worker.income(year, month)));

		sc.close();
	}

}
