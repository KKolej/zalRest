package com.kolej.bartosz.zalrest.security;

import com.kolej.bartosz.zalrest.customer.model.CustomUser;
import com.kolej.bartosz.zalrest.customer.model.Role;
import com.kolej.bartosz.zalrest.customer.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<CustomUser> myUser = userRepository.getMyUserByUsername(username);
        if (myUser.isPresent()) {

            return User.builder()
                    .username(myUser.get().getUsername())
                    .password(new BCryptPasswordEncoder()
                            .encode(myUser
                                    .get()
                                    .getPassword()))
                    .roles(myUser.get()
                            .getRoles()
                            .stream()
                            .map(Role::getAuthority)
                            .collect(Collectors.joining()))
                    .build();
        } else {
            throw new UsernameNotFoundException("User " + username + " not found.");
        }
    }
}
