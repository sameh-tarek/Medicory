package com.sameh.medicory;

import com.sameh.medicory.security.RsaKeyProperties;
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
