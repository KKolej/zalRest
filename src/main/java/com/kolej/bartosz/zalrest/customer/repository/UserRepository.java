package com.kolej.bartosz.zalrest.customer.repository;

import com.kolej.bartosz.zalrest.customer.model.CustomUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<CustomUser, UUID> {
    Optional<CustomUser> getMyUserByUsername(String username);
}
