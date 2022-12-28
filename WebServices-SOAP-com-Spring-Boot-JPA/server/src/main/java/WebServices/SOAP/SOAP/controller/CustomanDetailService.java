package WebServices.SOAP.SOAP.controller;

import WebServices.SOAP.SOAP.bean.Customer;
import WebServices.SOAP.SOAP.constants.Status;
import WebServices.SOAP.SOAP.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CustomanDetailService {

    @Autowired
    CustomerRepository repository;

    public Customer findById(Integer id) {
        Optional<Customer> op = repository.findById(id);
        if (op.isPresent()) {
            return op.get();
        }
        return null;
    }

    public List<Customer> findAll() {
        return repository.findAll();
    }

    public Status delete(Integer id) {
        try {
            repository.deleteById(id);
            return Status.SUCCESS;
        } catch (Exception e) {
            return Status.FAILURE;
        }
    }

    public Status insert(Customer c) {
        try {
            repository.save(c);
            return Status.SUCCESS;
        } catch (Exception e) {
            return Status.FAILURE;
        }
    }
}
