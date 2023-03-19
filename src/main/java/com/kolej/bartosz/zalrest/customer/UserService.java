package com.kolej.bartosz.zalrest.customer;

import com.kolej.bartosz.zalrest.customer.model.CustomUser;
import com.kolej.bartosz.zalrest.customer.model.SingUpCredentials;
import com.kolej.bartosz.zalrest.customer.model.UserSettings;
import com.kolej.bartosz.zalrest.customer.repository.RoleRepository;
import com.kolej.bartosz.zalrest.customer.repository.SettingsRepository;
import com.kolej.bartosz.zalrest.customer.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
class UserService {

    private final UserRepository userRepository;
    private final SettingsRepository settingsRepository;
    private final RoleRepository roleRepository;

    UserService(UserRepository userRepository, SettingsRepository settingsRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.settingsRepository = settingsRepository;
        this.roleRepository = roleRepository;
    }

    String createNewUser(SingUpCredentials singUpCredentials) {
        UserSettings userSettings = new UserSettings(singUpCredentials.getEmail(), singUpCredentials.getPhoneNumber());
        userSettings = settingsRepository.save(userSettings);
        CustomUser customUser = new CustomUser(
                singUpCredentials.getUsername(),
                "{bcrypt}" + new BCryptPasswordEncoder().encode(singUpCredentials.getPassword()),
                List.of(roleRepository.getRoleByName("USER").get()),
                userSettings);

        return userRepository.save(customUser).getUsername();
    }

    UserSettings getUserSettings(String name) {
        UserSettings userSettings = userRepository.getMyUserByUsername(
                        name
                ).orElseThrow(NoSuchElementException::new)
                .getUserSettings();
        return userSettings;
    }
}
