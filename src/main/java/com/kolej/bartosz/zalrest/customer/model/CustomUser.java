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
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition="UUID")
    private UUID id;
    @Column(unique = true)
    private String username;
    private String password;
    private boolean enabled;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles;
    @OneToOne(fetch = FetchType.EAGER)
    private UserSettings userSettings;
    public CustomUser(String username, String password, List<Role> roles, boolean isEnabled) {
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.enabled = isEnabled;
    }

    public CustomUser(String username, String password, List<Role> roles, UserSettings userSettings) {
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.userSettings = userSettings;
        this.enabled = true;
    }
}
