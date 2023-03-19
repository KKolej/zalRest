package com.kolej.bartosz.zalrest;

import com.kolej.bartosz.zalrest.customer.model.CustomUser;
import com.kolej.bartosz.zalrest.customer.repository.RoleRepository;
import com.kolej.bartosz.zalrest.model.Role;
import com.kolej.bartosz.zalrest.customer.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.PostConstruct;
import java.util.List;

@SpringBootApplication
public class ZalRestApplication {

    //all from openAPI https://www.baeldung.com/spring-rest-openapi-documentation
//http://localhost:8080/api-docs/
//http://localhost:8080/swagger-ui/index.html
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;

    public static void main(String[] args) {
        SpringApplication.run(ZalRestApplication.class, args);
    }

    @PostConstruct
    public void init() {
        System.out.println("Init start");

        Role roleUser = new Role("USER");
        Role roleAdmin = new Role("ADMIN");
        Role undefined = new Role("UNDEFINED");

        roleRepository.save(roleAdmin);
        roleRepository.save(roleUser);
        roleRepository.save(undefined);

        CustomUser myUserAdmin = new CustomUser("admin", "{bcrypt}" + new BCryptPasswordEncoder().encode("admin"),
                List.of(roleRepository.getRoleByName("ADMIN").orElse(undefined)), true);

        CustomUser userMyUser = new CustomUser("user", "{bcrypt}" + new BCryptPasswordEncoder().encode("user"),
                List.of(roleRepository.getRoleByName("USER").orElse(undefined)), true);

        userRepository.save(myUserAdmin);
        userRepository.save(userMyUser);
    }
}
