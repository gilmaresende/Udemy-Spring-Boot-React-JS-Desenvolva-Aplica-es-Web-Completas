package entities;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import entities.enums.WorkerLevel;

public class Worker {

	private String name;
	private WorkerLevel level;
	private Double salaryBase;

	private Department department;

	private List<HourContract> contracts = new ArrayList<HourContract>();

	public Worker(String name, WorkerLevel level, Double salaryBase, Department department) {

		this.name = name;
		this.level = level;
		this.salaryBase = salaryBase;
		this.department = department;
	}

	public Worker() {

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public WorkerLevel getLevel() {
		return level;
	}

	public void setLevel(WorkerLevel level) {
		this.level = level;
	}

	public Double getSalaryBase() {
		return salaryBase;
	}

	public void setSalaryBase(Double salaryBase) {
		this.salaryBase = salaryBase;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public List<HourContract> getContracts() {
		return contracts;
	}

	public void addContract(HourContract contract) {
		this.contracts.add(contract);
	}

	public void remeveContract(HourContract contract) {
		this.contracts.remove(contract);
	}

	public double income(int year, int month) {

		Double contra = getContracts().stream().filter(f -> {
			Calendar cal = Calendar.getInstance();
			cal.setTime(f.getDate());
			return cal.get(Calendar.YEAR) == year && cal.get(Calendar.DAY_OF_MONTH) + 1 == month;
		}).collect(Collectors.toList()).stream().mapToDouble(i -> i.getValuePerHour() * i.getHours()).sum();

		return contra + salaryBase;
	}

}
