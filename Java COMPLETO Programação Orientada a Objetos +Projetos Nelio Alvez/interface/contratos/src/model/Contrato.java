package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Contrato {

	private int number;

	private LocalDate date;

	private double totalValue;

	List<Parcela> parcelas = new ArrayList<>();

	public Contrato(int number, LocalDate date, double totalValue) {
		this.number = number;
		this.date = date;
		this.totalValue = totalValue;
	}

	public Contrato() {
	}

	/**
	 * @return the number
	 */
	public int getNumber() {
		return number;
	}

	/**
	 * @param number the number to set
	 */
	public void setNumber(int number) {
		this.number = number;
	}

	/**
	 * @return the date
	 */
	public LocalDate getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(LocalDate date) {
		this.date = date;
	}

	/**
	 * @return the totalValue
	 */
	public double getTotalValue() {
		return totalValue;
	}

	/**
	 * @param totalValue the totalValue to set
	 */
	public void setTotalValue(double totalValue) {
		this.totalValue = totalValue;
	}

	/**
	 * @return the parcelas
	 */
	public List<Parcela> getParcelas() {
		return parcelas;
	}

	/**
	 * @return the parcelas
	 */
	public void addParcelas(Parcela parcela) {
		this.parcelas.add(parcela);
	}

}
