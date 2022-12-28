package WebServices.SOAP.SOAP.exception;

import org.springframework.ws.soap.server.endpoint.annotation.FaultCode;
import org.springframework.ws.soap.server.endpoint.annotation.SoapFault;

//@SoapFault(faultCode = FaultCode.CLIENT)
@SoapFault(faultCode = FaultCode.CLIENT, customFaultCode = "{123}")
public class CustomeNotFond extends RuntimeException {

	public CustomeNotFond(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

}
