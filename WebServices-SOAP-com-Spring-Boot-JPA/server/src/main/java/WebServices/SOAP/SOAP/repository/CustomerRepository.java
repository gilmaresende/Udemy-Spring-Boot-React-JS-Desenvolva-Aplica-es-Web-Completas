package WebServices.SOAP.SOAP.repository;

import WebServices.SOAP.SOAP.bean.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}
