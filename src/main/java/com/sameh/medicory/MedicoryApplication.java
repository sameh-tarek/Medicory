package com.sameh.medicory;

import com.sameh.medicory.entity.usersEntities.Owner;
import com.sameh.medicory.mapper.OwnerMapper;
import com.sameh.medicory.model.owner.OwnerDTO;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MedicoryApplication {

    public static void main(String[] args) {
        SpringApplication.run(MedicoryApplication.class, args);
    }

}
