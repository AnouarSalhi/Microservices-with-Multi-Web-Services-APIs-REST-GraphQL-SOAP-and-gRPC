package com.salhi.customerservice.web.grpc;

import com.salhi.customerservice.entities.Customer;
import com.salhi.customerservice.mappers.CustomerMapper;
import com.salhi.customerservice.repositories.CustomerRepository;
import com.salhi.customerservice.stub.CustomerServiceGrpc;
import com.salhi.customerservice.stub.CustomerServiceOuterClass;
import io.grpc.stub.StreamObserver;
import lombok.AllArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

import java.util.List;
import java.util.stream.Collectors;

import io.grpc.Status;
import io.grpc.StatusRuntimeException;

@GrpcService
@AllArgsConstructor
public class CustomerGrpcController extends CustomerServiceGrpc.CustomerServiceImplBase {
  private CustomerRepository customerRepository;
  private CustomerMapper customerMapper;

    @Override
    public void getAllCustomers(CustomerServiceOuterClass.GetAllCustomersRequest request, StreamObserver<CustomerServiceOuterClass.GetCustomersResponse> responseObserver) {
        List<Customer> customers= customerRepository.findAll();
        List<CustomerServiceOuterClass.Customer> grpcCustomers = customers.stream().map(customerMapper::customerToGrpcCustomer).collect(Collectors.toList());

        CustomerServiceOuterClass.GetCustomersResponse customersResponse=
                CustomerServiceOuterClass.GetCustomersResponse.newBuilder()
                        .addAllCustomers(grpcCustomers)
                        .build();
        responseObserver.onNext(customersResponse);
        responseObserver.onCompleted();
    }



    @Override
    public void getCustomerById(CustomerServiceOuterClass.GetCustomerByIdRequest request, StreamObserver<CustomerServiceOuterClass.GetCustomerByIdResponse> responseObserver) {
        try {
            // Validate the request
            if (request == null) {
                throw new StatusRuntimeException(
                        Status.INVALID_ARGUMENT.withDescription("Customer ID is required and cannot be null or empty")
                );
            }

            // Fetch the customer from the repository
            Customer customerEntity = customerRepository.findById(request.getCustomerId())
                    .orElseThrow(() -> new StatusRuntimeException(
                            Status.NOT_FOUND.withDescription("Customer not found with ID: " + request.getCustomerId())
                    ));

            // Map the customer entity to the gRPC response
            CustomerServiceOuterClass.GetCustomerByIdResponse response =
                    CustomerServiceOuterClass.GetCustomerByIdResponse.newBuilder()
                            .setCustomer(customerMapper.customerToGrpcCustomer(customerEntity))
                            .build();

            // Send the response to the client
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (StatusRuntimeException e) {
            // Propagate gRPC-specific errors
            responseObserver.onError(e);
        } catch (Exception e) {
            // Handle unexpected errors
            responseObserver.onError(
                    new StatusRuntimeException(
                            Status.INTERNAL.withDescription("Internal server error: " + e.getMessage())
                    )
            );
        }
    }

    @Override
    public void saveCustomer(CustomerServiceOuterClass.SaveCustomerRequest request, StreamObserver<CustomerServiceOuterClass.SaveCustomerResponse> responseObserver) {
        Customer customer=customerMapper.grpcCustomerToCustomer(request.getCustomer());
       Customer savedCustomer= customerRepository.save(customer);
        CustomerServiceOuterClass.SaveCustomerResponse saveCustomerResponse= CustomerServiceOuterClass.SaveCustomerResponse.newBuilder()
                .setCustomer(customerMapper.customerToGrpcCustomer(savedCustomer))
                .build();
        responseObserver.onNext(saveCustomerResponse);
        responseObserver.onCompleted();
    }
}
