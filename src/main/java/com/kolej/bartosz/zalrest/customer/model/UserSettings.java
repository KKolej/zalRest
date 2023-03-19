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

    public UserSettings(String email, int phoneNumber) {
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public UserSettings() {
    }
}
