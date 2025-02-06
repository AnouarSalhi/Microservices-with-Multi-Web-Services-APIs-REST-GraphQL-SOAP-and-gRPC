package com.salhi.customerservice.web.graphql;

import com.salhi.customerservice.dto.CustomerRequest;
import com.salhi.customerservice.entities.Customer;
import com.salhi.customerservice.mappers.CustomerMapper;
import com.salhi.customerservice.repositories.CustomerRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class CustomerGraphqlController {
    private CustomerRepository customerRepository;
    private CustomerMapper customerMapper;

    public CustomerGraphqlController(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }




    @QueryMapping
    public List<Customer> customersList(){
        return customerRepository.findAll();
    }

    @QueryMapping
    public Optional<Customer> customerById(@Argument Long id){
        Optional<Customer> customer=customerRepository.findById(id);
        if(!customer.isPresent()) throw new RuntimeException(String.format("Customer with id : %s not found!",id));
        return customerRepository.findById(id);
    }

        @MutationMapping
        public Customer saveCustomer(@Argument CustomerRequest customer){
            Customer c=customerMapper.from(customer);
        return customerRepository.save(c);
    }
}
