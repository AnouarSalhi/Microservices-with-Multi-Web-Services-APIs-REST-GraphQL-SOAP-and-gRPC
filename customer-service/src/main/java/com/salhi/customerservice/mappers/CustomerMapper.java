package com.salhi.customerservice.mappers;

import com.salhi.customerservice.dto.CustomerRequest;
import com.salhi.customerservice.entities.Customer;
import com.salhi.customerservice.stub.CustomerServiceOuterClass;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {
    private ModelMapper modelMapper=new ModelMapper();
    public Customer from(CustomerRequest customerRequest){
        return  modelMapper.map(customerRequest,Customer.class);
    }

    public CustomerServiceOuterClass.Customer customerToGrpcCustomer(Customer customer){
        return  modelMapper.map(customer,CustomerServiceOuterClass.Customer.Builder.class).build();
    }

    public Customer grpcCustomerToCustomer(CustomerServiceOuterClass.CustomerRequest customer){
        return  modelMapper.map(customer,Customer.class);
    }
}
