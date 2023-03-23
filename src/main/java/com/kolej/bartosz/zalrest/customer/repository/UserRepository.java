package com.kolej.bartosz.zalrest.customer.repository;

import com.kolej.bartosz.zalrest.customer.model.CustomUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<CustomUser, UUID> {
    Optional<CustomUser> getMyUserByUsername(String username);
    void deleteByUsername(String username);
}
