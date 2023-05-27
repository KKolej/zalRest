package com.kolej.bartosz.zalrest.customer.model;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Entity
@ToString
@Getter
@Setter
public class UserSettings {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition="UUID")
    private UUID id;
    private int phoneNumber;
    private String email;
    private String username;

    public UserSettings(String email, int phoneNumber, String username) {
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.username = username;
    }

    public UserSettings() {
    }
}
