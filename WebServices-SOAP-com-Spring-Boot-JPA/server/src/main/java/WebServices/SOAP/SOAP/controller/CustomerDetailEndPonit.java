package WebServices.SOAP.SOAP.controller;

import java.util.List;

import br.com.klayrocha.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import WebServices.SOAP.SOAP.bean.Customer;
import WebServices.SOAP.SOAP.constants.Status;
import WebServices.SOAP.SOAP.exception.CustomeNotFond;

@Endpoint
public class CustomerDetailEndPonit {

    @Autowired
    CustomanDetailService customanDetailService;

    @PayloadRoot(namespace = "http://klayrocha.com.br", localPart = "GetCustomerDetailRequest")
    @ResponsePayload
    public GetCustomerDetailResponse processCustomerDetailRequest(@RequestPayload GetCustomerDetailRequest req)
            throws Exception {

        System.out.println(req.getId());

        Customer c = customanDetailService.findById(req.getId());
        if (c == null) {
            throw new CustomeNotFond("Invalid customer id" + req.getId());
        }
        GetCustomerDetailResponse response = convertToCustomerDetailResponse(c);

        return response;
    }

    private GetCustomerDetailResponse convertToCustomerDetailResponse(Customer c) {
        GetCustomerDetailResponse g = new GetCustomerDetailResponse();
        g.setCustomerDetail(convertToCustomerDetail(c));
        return g;
    }

    @PayloadRoot(namespace = "http://klayrocha.com.br", localPart = "GetAllCustomerRequest")
    @ResponsePayload
    public GetAllCustomerResponse processCustomerAllDetailResponse(@RequestPayload GetAllCustomerRequest req)
            throws Exception {

        List<Customer> c = customanDetailService.findAll();
        if (c == null) {
            throw new Exception("Sem registros");
        }
        GetAllCustomerResponse response = convertToAllCustomerResponse(c);

        return response;
    }

    private CustomerDetail convertToCustomerDetail(Customer c) {
        CustomerDetail cd = new CustomerDetail();
        cd.setId(c.getId());
        cd.setNome(c.getNome());
        cd.setPhone(c.getPhone());
        cd.setEmail(c.getEmail());
        return cd;
    }

    private GetAllCustomerResponse convertToAllCustomerResponse(List<Customer> cutomers) {
        GetAllCustomerResponse g = new GetAllCustomerResponse();

        cutomers.forEach(i -> g.getCustomerDetail().add(convertToCustomerDetail(i)));
        return g;
    }

    @PayloadRoot(namespace = "http://klayrocha.com.br", localPart = "DeleteCustomerRequest")
    @ResponsePayload
    public DeleteCustomerResponse processCustomerDeleteDetailResponse(@RequestPayload DeleteCustomerRequest req)
            throws Exception {
        Status status = customanDetailService.delete(req.getId());
        DeleteCustomerResponse res = new DeleteCustomerResponse();
        res.setStatus(convertToStatusXML(status));
        return res;
    }

    private br.com.klayrocha.Status convertToStatusXML(Status status) {
        switch (status) {
            case SUCCESS:
                return br.com.klayrocha.Status.SUCCESS;
            default:
                return br.com.klayrocha.Status.FAILURE;
        }
    }

    @PayloadRoot(namespace = "http://klayrocha.com.br", localPart = "InsertCustomerRequest")
    @ResponsePayload
    public InsertCustomerResponse processCustomerDeleteDetailResponse(@RequestPayload InsertCustomerRequest req)
            throws Exception {
        InsertCustomerResponse res = new InsertCustomerResponse();
        Status status = customanDetailService.insert(convertCustomer(req.getCustomerDetail()));
        res.setStatus(convertToStatusXML(status));
        return res;
    }

    private Customer convertCustomer(CustomerDetail cd) {
        return new Customer(cd.getId(), cd.getNome(), cd.getPhone(), cd.getEmail());
    }
}
