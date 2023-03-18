package com.kolej.bartosz.zalrest.model;

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
    private List<Role> roles;

    public CustomUser(String username, String password, List<Role> roles, boolean isEnabled) {
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.enabled = isEnabled;
    }
}
