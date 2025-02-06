package com.salhi.customerservice.web.soapApi;


import com.salhi.customerservice.dto.CustomerRequest;
import com.salhi.customerservice.entities.Customer;
import com.salhi.customerservice.mappers.CustomerMapper;
import com.salhi.customerservice.repositories.CustomerRepository;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@WebService(serviceName = "CustomerWS")
@AllArgsConstructor
@Component
public class CustomerSoapController {
    private CustomerRepository customerRepository;
    private CustomerMapper customerMapper;
    @WebMethod
  public List<Customer> customerList(){
      return customerRepository.findAll();
  }
  @WebMethod
  public Customer customerById(@WebParam Long id){
        return customerRepository.findById(id).get();
  }

  @WebMethod
  public Customer saveCustomer(@WebParam(name = "customer") CustomerRequest customerRequest){
        Customer customer=customerMapper.from(customerRequest);
         return customerRepository.save(customer);
    }
}
