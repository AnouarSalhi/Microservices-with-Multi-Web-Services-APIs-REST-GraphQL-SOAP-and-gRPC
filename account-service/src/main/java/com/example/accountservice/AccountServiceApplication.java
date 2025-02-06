package com.example.accountservice;

import com.salhi.customerservice.web.soapapi.CustomerSoapController;
import com.salhi.customerservice.web.soapapi.CustomerWS;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableFeignClients
public class AccountServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountServiceApplication.class, args);
    }

    @Bean
    RestTemplate restTemplate(){
        return new RestTemplate();
    }
    @Bean
    CustomerSoapController customerSoapController(){
        return new CustomerWS().getCustomerSoapControllerPort();
    }
}
