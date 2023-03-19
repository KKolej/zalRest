package com.kolej.bartosz.zalrest.security;

import com.kolej.bartosz.zalrest.customer.model.CustomUser;
import com.kolej.bartosz.zalrest.customer.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CustomAuthenticationManager implements AuthenticationManager {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public CustomAuthenticationManager(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getPrincipal() + "";
        String password = authentication.getCredentials() + "";

        Optional<CustomUser> user = userRepository.getMyUserByUsername(username);
        if (user.isEmpty()) {
            throw new BadCredentialsException("1000");
        }
        if (!passwordEncoder.matches(password, user.get().getPassword())) {
            throw new BadCredentialsException("1000");
        }
        if (!user.get().isEnabled()) {
            throw new DisabledException("1001");
        }
        return new UsernamePasswordAuthenticationToken(username, null, user.get().getRoles());
    }
}
