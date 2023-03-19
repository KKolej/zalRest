package com.kolej.bartosz.zalrest.customer.repository;

import com.kolej.bartosz.zalrest.customer.model.UserSettings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SettingsRepository extends JpaRepository<UserSettings, UUID> {
}
