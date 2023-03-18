package com.kolej.bartosz.zalrest.repository;

import com.kolej.bartosz.zalrest.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, UUID> {
    Optional<Role> getRoleByName(String name);
}
