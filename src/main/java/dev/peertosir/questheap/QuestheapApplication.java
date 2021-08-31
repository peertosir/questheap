package dev.peertosir.questheap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class QuestheapApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuestheapApplication.class, args);
    }

}
