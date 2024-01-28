package com.example;

import com.example.persistence.entity.order.Order;
import com.example.persistence.entity.user.Admin;
import com.example.persistence.entity.user.Personal;
import com.example.persistence.repository.order.OrderRepository;
import com.example.persistence.repository.user.AdminRepository;
import com.example.persistence.repository.user.PersonalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;

@SpringBootApplication
public class FinalProjectServerApplication {

    //    @Autowired  //TODO убрать после експериментов
//    private AdminRepository adminRepository;
    @Autowired  //TODO убрать после експериментов
    private OrderRepository orderRepository;

    @Autowired  //TODO убрать после експериментов
    private PersonalRepository personalRepository;
//
    @Autowired
    private PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(FinalProjectServerApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    /*TODO убрать после експериментов*/
    public void run() {
        Order order = new Order();
        orderRepository.save(order);


//        Personal personal = new Personal();
//        personal.setLogin("login2@mail.com");
//
//        personal.setPassword(passwordEncoder.encode("password"));
//        personalRepository.save(personal);
    }
}
