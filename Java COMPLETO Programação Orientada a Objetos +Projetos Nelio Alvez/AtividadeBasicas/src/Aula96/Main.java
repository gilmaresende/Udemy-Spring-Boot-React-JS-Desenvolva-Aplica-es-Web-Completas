package Aula96;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		List<Employee> empoyees = new ArrayList();
		System.out.println("How many employees will be registered?");
		Integer theAmount = sc.nextInt();

		for (int i = 0; i < theAmount; i++) {
			System.out.println("Emplyoee #" + (i + 1));
			System.out.println("Id:");

			Integer id = sc.nextInt();
			System.out.println("Name:");
			sc.nextLine();
			String name = sc.nextLine();
			System.out.println("Salary:");
			Double wage = sc.nextDouble();
			Employee e = new Employee(name, id, wage);
			empoyees.add(e);
		}

		System.out.println("Enter the employyer id that will have salary increase:");
		Integer idIncrease = sc.nextInt();
		System.out.println("Enter the percentage:");
		Double percentage = sc.nextDouble();

		Employee employeeIncrease = empoyees.stream().filter(aux -> aux.getId() == idIncrease).findFirst().orElse(null);

		if (employeeIncrease != null) {
			employeeIncrease.setWage(employeeIncrease.getWage() * ((percentage / 100) + 1d));
		} else {
			System.out.println("This id does not exist!");
		}

		for (Employee e : empoyees) {
			System.out.println("Id." + e.getId() + " - Nome: " + e.getName() + " - " + e.getWage());
		}

		sc.close();

	}

}
