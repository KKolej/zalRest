package com.kolej.bartosz.zalrest.customer.model;

import lombok.Data;
import lombok.Getter;
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
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
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

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public UserSettings getUserSettings() {
        return userSettings;
    }

    public void setUserSettings(UserSettings userSettings) {
        this.userSettings = userSettings;
    }
}
