package services.impl;

import java.time.LocalDate;

import model.Contrato;
import model.Parcela;
import services.interfaces.OnlinePaymentService;

public class ContractService {

	private OnlinePaymentService onlinePaymentService;

	public ContractService(OnlinePaymentService onlinePaymentService) {
		this.onlinePaymentService = onlinePaymentService;
	}

	public void processContract(Contrato contract, int qtd) {
		Double basicQuata = contract.getTotalValue() / qtd;

		for (int i = 1; i <= qtd; i++) {
			LocalDate dueDatet = contract.getDate().plusMonths(i);
			double interent = onlinePaymentService.interest(basicQuata, i);
			double fee = onlinePaymentService.paymentFree(interent + basicQuata);
			double quota = basicQuata + fee + interent;
			contract.addParcelas(new Parcela(quota, dueDatet));
		}
	}
}
