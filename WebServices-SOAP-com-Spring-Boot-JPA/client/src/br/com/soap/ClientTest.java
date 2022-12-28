package br.com.soap;

import javax.xml.ws.handler.HandlerResolver;

import br.com.klayrocha.CustomerPort;
import br.com.klayrocha.CustomerPortService;
import br.com.klayrocha.GetCustomerDetailRequest;
import br.com.klayrocha.GetCustomerDetailResponse;

public class ClientTest {

	public static void main(String[] args) {
		CustomerPortService service = new CustomerPortService();
		
		HandlerResolver hr = new HeaderHandlerResolver();
		service.setHandlerResolver(hr);

		CustomerPort port = service.getCustomerPortSoap11();

		GetCustomerDetailRequest customerDetailRequest = new GetCustomerDetailRequest();

		customerDetailRequest.setId(1);

		GetCustomerDetailResponse customerDetailResponse = port.getCustomerDetail(customerDetailRequest);

		System.out.println(" id -> " + customerDetailResponse.getCustomerDetail().getId());
		System.out.println(" nome -> " + customerDetailResponse.getCustomerDetail().getNome());
		System.out.println(" email -> " + customerDetailResponse.getCustomerDetail().getEmail());
		System.out.println(" phone -> " + customerDetailResponse.getCustomerDetail().getPhone());

	}

}
