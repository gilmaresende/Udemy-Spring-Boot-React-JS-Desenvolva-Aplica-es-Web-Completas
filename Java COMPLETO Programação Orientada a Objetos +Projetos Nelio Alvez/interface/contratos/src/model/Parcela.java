package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Parcela {
	
	private static	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	private double valor;

	private LocalDate dueDate;

	public Parcela() {
	}

	public Parcela(double valor, LocalDate dueDate) {
		this.valor = valor;
		this.dueDate = dueDate;
	}

	/**
	 * @return the valor
	 */
	public double getValor() {
		return valor;
	}

	/**
	 * @param valor the valor to set
	 */
	public void setValor(double valor) {
		this.valor = valor;
	}

	/**
	 * @return the dueDate
	 */
	public LocalDate getDueDate() {
		return dueDate;
	}

	/**
	 * @param dueDate the dueDate to set
	 */
	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	@Override
	public String toString() {
		String str = dueDate.format(dtf) + " - " + String.format("%.2f", valor);
		return str;
	}
	
	

}
