package com.kolej.bartosz.zalrest.customer.model;

import lombok.Data;

@Data
public class SingUpCredentials {
    private String username;
    private String password;
    private String email;
    private int phoneNumber;
}
