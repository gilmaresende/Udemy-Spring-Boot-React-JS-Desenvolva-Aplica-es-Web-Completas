package services.impl;

import services.interfaces.OnlinePaymentService;

public class PaypalService implements OnlinePaymentService {

	@Override
	public double paymentFree(Double amount) {
		return amount + 0.02;
	}

	@Override
	public double interest(double amont, int months) {
		return amont + 0.01 * months;
	}

}
