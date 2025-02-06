package com.example.accountservice.mappers;

import com.example.accountservice.dto.CustomerResponseDto;
import com.example.accountservice.model.Customer;

import com.salhi.customerservice.stub.CustomerServiceOuterClass;
import org.springframework.stereotype.Component;
import org.modelmapper.ModelMapper;
@Component
public class CustomerMapper {
    private ModelMapper modelMapper=new ModelMapper();
    public Customer fromSoapCustomer(com.salhi.customerservice.web.soapapi.Customer soapCustomer){
        return modelMapper.map(soapCustomer,Customer.class);
    }

    public CustomerResponseDto fromGrpcToCustomerRespnseDto(CustomerServiceOuterClass.Customer customer){
        return modelMapper.map(customer,CustomerResponseDto.class);
    }

}