package com.kolej.bartosz.zalrest.customer.repository;

import com.kolej.bartosz.zalrest.customer.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RoleRepository extends JpaRepository<Role, UUID> {
    Optional<Role> getRoleByName(String name);
}
