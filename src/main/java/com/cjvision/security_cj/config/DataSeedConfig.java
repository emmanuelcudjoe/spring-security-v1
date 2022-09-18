package com.cjvision.security_cj.config;

import com.cjvision.security_cj.Entity.Role;
import com.cjvision.security_cj.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DataSeedConfig implements CommandLineRunner {

    @Autowired
    RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {
        Role admin = new Role("ROLE_ADMIN");
        Role customer = new Role("ROLE_CUSTOMER");
        Role user = new Role("ROLE_USER");
        Role editor = new Role("ROLE_EDITOR");

        var savedRoles = roleRepository.saveAll(List.of(
                admin,
                customer,
                editor,
                user
        ));

        System.out.println(savedRoles);
    }
}
