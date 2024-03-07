package com.atakan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients // Feign Client'ı aktif hale getiriyoruz.
public class UserMicroServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserMicroServiceApplication.class);
    }
}
