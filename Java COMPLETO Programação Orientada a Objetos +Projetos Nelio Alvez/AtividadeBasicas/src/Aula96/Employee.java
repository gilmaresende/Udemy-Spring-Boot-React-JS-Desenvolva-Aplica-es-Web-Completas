package Aula96;

public class Employee {// Funcionario

	String name;
	Integer id;
	Double wage;// salario

	public Employee(String name, Integer id, Double wage) {
		this.name = name;
		this.id = id;
		this.wage = wage;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getWage() {
		return wage;
	}

	public void setWage(Double wage) {
		this.wage = wage;
	}

}
