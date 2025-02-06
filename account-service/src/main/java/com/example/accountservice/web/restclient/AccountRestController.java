package com.example.accountservice.web.restclient;

import com.example.accountservice.feign.CustomerRestClient;
import com.example.accountservice.model.Customer;
import com.salhi.customerservice.web.soapapi.CustomerSoapController;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("account-service/rest")
public class AccountRestController {



    private RestTemplate restTemplate;

    public AccountRestController(RestTemplate restTemplate, CustomerRestClient customerRestClient) {
        this.restTemplate = restTemplate;
        this.customerRestClient = customerRestClient;
    }

    private CustomerRestClient customerRestClient;

    @GetMapping("/customers")
    List<Customer> customerList(){
       Customer [] c= restTemplate.getForObject("http://localhost:8082/customers", Customer[].class);
       return  List.of(c);
    }
    @GetMapping("/customers/{id}")
    Customer customerById(@PathVariable Long id){
        Customer  c= restTemplate.getForObject("http://localhost:8082/customers/"+id, Customer.class);
        return  c;
    }

    @GetMapping("/customers/v2")
    Flux<Customer> customerList2(){
        WebClient webClient=WebClient.builder()
                .baseUrl("http://localhost:8082")
        .build();

        Flux<Customer> customerFlux=webClient.get()
                .uri("/customers")
                .retrieve().bodyToFlux(Customer.class);
        return customerFlux;
    }

    @GetMapping("/customers/v2/{id}")
    Mono<Customer> customerById2(@PathVariable Long id){
        WebClient webClient=WebClient.builder()
                .baseUrl("http://localhost:8082")
                .build();

        Mono<Customer> customerFlux=webClient.get()
                .uri("customers/"+id)
                .retrieve().bodyToMono(Customer.class);
        return customerFlux;
    }

    @GetMapping("/customers/v3")
    List<Customer> customerListV3(){
        return  customerRestClient.getCustomers();
    }

    @GetMapping("/customers/v3/{id}")
   Customer customerByIdV3(@PathVariable Long id){
        return  customerRestClient.getCustomerById(id);
    }



}
