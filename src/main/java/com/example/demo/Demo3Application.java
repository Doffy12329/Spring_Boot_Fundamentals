package com.example.demo;


import com.example.demo.services.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Demo3Application {

    public static void main(String[] args)
    {
        //IOC OR INVERSION OF CONTROL
       ApplicationContext context = SpringApplication.run(Demo3Application.class, args);
       var service = context.getBean(UserService.class);
       service.fetchUsers();






    }




}
