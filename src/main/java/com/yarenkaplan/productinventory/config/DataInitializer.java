package com.yarenkaplan.productinventory.config;

import com.yarenkaplan.productinventory.entity.Role;
import com.yarenkaplan.productinventory.enums.RoleEnum;
import com.yarenkaplan.productinventory.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;

    public DataInitializer(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if(roleRepository.findByName(RoleEnum.USER).isEmpty()){
            Role userRole = new Role();
            userRole.setName(RoleEnum.USER);
            roleRepository.save(userRole);
        }

        if(roleRepository.findByName(RoleEnum.ADMIN).isEmpty()){
            Role adminRole = new Role();
            adminRole.setName(RoleEnum.ADMIN);
            roleRepository.save(adminRole);
        }
    }
}
