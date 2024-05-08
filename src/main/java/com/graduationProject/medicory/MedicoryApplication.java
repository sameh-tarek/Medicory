package com.graduationProject.medicory;

import com.graduationProject.medicory.security.RsaKeyProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(RsaKeyProperties.class)
@SpringBootApplication
public class MedicoryApplication {

    public static void main(String[] args) {
        SpringApplication.run(MedicoryApplication.class, args);
    }

}
