package com.salhi.customerservice.web.restApi;

import com.salhi.customerservice.entities.Customer;
import com.salhi.customerservice.repositories.CustomerRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CustomerRestController {
    public CustomerRestController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    private CustomerRepository customerRepository;

    @GetMapping("/customers")
    public List<Customer> customersList(){
        return customerRepository.findAll();
    }

    @GetMapping("/customers/{id}")
    public Optional<Customer> customerById(@PathVariable Long id){
        return customerRepository.findById(id);
    }

    @PostMapping("customers")
    public Customer saveCustomer(@RequestBody Customer customer){
        return customerRepository.save(customer);
    }

}
