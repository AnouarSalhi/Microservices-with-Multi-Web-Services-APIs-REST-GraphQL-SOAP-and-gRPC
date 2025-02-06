package com.example.accountservice.web.grpcClient;

import com.example.accountservice.dto.CustomerResponseDto;
import com.example.accountservice.mappers.CustomerMapper;
import com.example.accountservice.model.Customer;
import com.salhi.customerservice.stub.CustomerServiceGrpc;
import com.salhi.customerservice.stub.CustomerServiceOuterClass;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("account-service/grpc")
public class AccountGrpcController {
    @GrpcClient(value = "customerService")
    private CustomerServiceGrpc.CustomerServiceBlockingStub customerServiceBlockingStub;
    private final CustomerMapper customerMapper;

    public AccountGrpcController(CustomerMapper customerMapper) {
        this.customerMapper = customerMapper;
    }

    @GetMapping("customers")
   public List<CustomerResponseDto> customerList(){
       CustomerServiceOuterClass.GetAllCustomersRequest request= CustomerServiceOuterClass.GetAllCustomersRequest.newBuilder().build();
        CustomerServiceOuterClass.GetCustomersResponse response= customerServiceBlockingStub.getAllCustomers(request);
        List<CustomerServiceOuterClass.Customer> customers=response.getCustomersList();
        List<CustomerResponseDto> customerResponseDtos=customers.stream().map(c->customerMapper.fromGrpcToCustomerRespnseDto(c)).collect(Collectors.toList());
        return customerResponseDtos;

   }

   @GetMapping("customers/{id}")
    public CustomerResponseDto customerById(@PathVariable Long id){
       CustomerServiceOuterClass.GetCustomerByIdRequest request= CustomerServiceOuterClass.GetCustomerByIdRequest.newBuilder()
               .setCustomerId(id)
               .build();
       CustomerServiceOuterClass.GetCustomerByIdResponse response= customerServiceBlockingStub.getCustomerById(request);
       var customer= response.getCustomer();
       return customerMapper.fromGrpcToCustomerRespnseDto(customer);
   }


   @PostMapping("customers")
    public CustomerResponseDto saveCustomer(@RequestBody Customer customer){
       CustomerServiceOuterClass.CustomerRequest customerRequest= CustomerServiceOuterClass.CustomerRequest.newBuilder()
               .setName(customer.name())
               .setEmail(customer.name())
               .build();
       CustomerServiceOuterClass.SaveCustomerRequest request= CustomerServiceOuterClass.SaveCustomerRequest.newBuilder()
               .setCustomer(customerRequest)
               .build();

       CustomerServiceOuterClass.SaveCustomerResponse response= customerServiceBlockingStub.saveCustomer(request);
       System.out.println(response.getCustomer().getEmail());
       return customerMapper.fromGrpcToCustomerRespnseDto(response.getCustomer());

    }
}
