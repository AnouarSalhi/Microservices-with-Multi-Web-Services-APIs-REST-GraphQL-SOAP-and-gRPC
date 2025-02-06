package com.salhi.customerservice.dto;


import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor
public class CustomerRequest{
    private String name;
    private String email;
}
