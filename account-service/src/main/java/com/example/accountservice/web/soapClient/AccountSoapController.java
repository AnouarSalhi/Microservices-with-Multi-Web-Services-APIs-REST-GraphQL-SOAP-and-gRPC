package com.example.accountservice.web.soapClient;

import com.example.accountservice.mappers.CustomerMapper;
import com.salhi.customerservice.web.soapapi.Customer;
import com.salhi.customerservice.web.soapapi.CustomerRequest;
import com.salhi.customerservice.web.soapapi.CustomerSoapController;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("account-service/soap")
public class AccountSoapController {
   private CustomerSoapController customerSoapController;
  private CustomerMapper customerMapper;
    public AccountSoapController(CustomerSoapController customerSoapController,CustomerMapper customerMapper) {
        this.customerSoapController = customerSoapController;
        this.customerMapper=customerMapper;
    }

    @GetMapping("customers")
    public List<Customer> customerList(){
        return customerSoapController.customerList();
    }
    @GetMapping("/customers/{id}")
    public Customer customerByIdSoap(@PathVariable Long id){
        Customer soapCustomer = customerSoapController.customerById(id);
        return soapCustomer;
    }
    @PostMapping("customers")
    public Customer saveCustomer(@RequestBody CustomerRequest customer){
        return customerSoapController.saveCustomer(customer);
    }


}
