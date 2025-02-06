package com.example.accountservice.web.graphqlclient;

import com.example.accountservice.model.Customer;
import org.springframework.graphql.client.HttpGraphQlClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("account-service/gql/customers")
public class AccountGqlController {
    @GetMapping("/{id}")
    public Mono<Customer> customerById(@PathVariable Long id){
        HttpGraphQlClient graphQlClient=HttpGraphQlClient
                .builder()
                .url("http://localhost:8082/graphql")
                .build();

        var httpRequestDocument= """
                query($id:Int){
                customerById(id:$id){
                id,name,email
                }
                }
                """;

        Mono<Customer> customerById=graphQlClient
                .document(httpRequestDocument)
                .variable("id",id)
                .retrieve("customerById")
                .toEntity(Customer.class);
        return customerById;
    }

    @GetMapping
    public Mono<List<Customer>> customerList(){
        HttpGraphQlClient graphQlClient=HttpGraphQlClient
                .builder()
                .url("http://localhost:8082/graphql")
                .build();

        var httpRequestDocument= """
                query{
                customersList{
                id,name,email
                }
                }
                """;

        Mono<List<Customer>> customerById=graphQlClient
                .document(httpRequestDocument)
                .retrieve("customersList")
                .toEntityList(Customer.class);
        return customerById;
    }
}
