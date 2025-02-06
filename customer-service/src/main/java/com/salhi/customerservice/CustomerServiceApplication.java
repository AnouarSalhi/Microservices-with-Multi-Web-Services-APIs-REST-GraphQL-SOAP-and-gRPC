package com.salhi.customerservice;

import com.salhi.customerservice.entities.Customer;
import com.salhi.customerservice.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CustomerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(CustomerRepository customerRepository) {
        return args -> {
            Customer customer1 = new Customer(null, "Hassan", "hassan@gmail.com");
            Customer customer2 = new Customer(null, "Amine", "amine@gmail.com");
            Customer customer3 = new Customer(null, "iman", "iman@gmail.com");

            customerRepository.save(customer1);
            customerRepository.save(customer2);
            customerRepository.save(customer3);

        };
    }
}
