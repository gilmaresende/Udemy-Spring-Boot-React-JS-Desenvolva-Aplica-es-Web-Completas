package services.interfaces;

public interface OnlinePaymentService {
	
	double paymentFree(Double amount);
	
	double interest(double amont, int months);

}
