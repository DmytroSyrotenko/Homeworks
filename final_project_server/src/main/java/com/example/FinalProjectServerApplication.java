package com.example;

import com.example.persistence.entity.user.Admin;
import com.example.persistence.repository.user.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class FinalProjectServerApplication {

    @Autowired  //TODO убрать после експериментов
    private AdminRepository adminRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(FinalProjectServerApplication.class, args);
    }

//    @EventListener(ApplicationReadyEvent.class)
//    /*TODO убрать после експериментов*/
//    public void run() {
//
//        Admin admin = new Admin();
//        admin.setLogin("login@mail.com");
//        admin.setPassword(passwordEncoder.encode("password"));
//        adminRepository.save(admin);
//    }
}
