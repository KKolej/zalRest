package com.kolej.bartosz.zalrest.customer.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
public class CustomUser {

    @Id
    @GeneratedValue
    private UUID id;
    private String username;
    private String password;
    private boolean enabled;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<com.kolej.bartosz.zalrest.model.Role> roles;
    public CustomUser(String username, String password, List<com.kolej.bartosz.zalrest.model.Role> roles, boolean isEnabled) {
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.enabled = isEnabled;
    }
}
