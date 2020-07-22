package com.trace;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@RestController
public class Service1Application {

    public static void main(String[] args) {
        SpringApplication.run(Service1Application.class, args);
    }

    @Autowired
    RestTemplate restTemplate;

    @Bean
    RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

    @RequestMapping("/test")
    public String test(){
        System.out.println("Service-1 called... from /test");
        return "Svc1 is responding ...";
    }
    
    @RequestMapping("/call1")
    public String callService1(){
        return "Service1 called...";
    }

    @RequestMapping("/call2")
    public String callService2(){
        System.out.println("Make call2...");
        return restTemplate.getForObject("http://localhost:8772/call2", String.class);
    }

    @RequestMapping("/call3")
    public String callService3(){
        System.out.println("Make call3 ...");
        return restTemplate.getForObject("http://localhost:8772/call3", String.class);
    }
}
