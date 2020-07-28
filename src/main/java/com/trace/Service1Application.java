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
        return restTemplate.getForObject("http://svctwo.microservices:8080/call2", String.class);
    }

    @RequestMapping("/call3")
    public String callService3(){
        System.out.println("Make call3 ...");
        return restTemplate.getForObject("http://svctwo.microservices:8080/call3", String.class);
    }
    
    
    @RequestMapping("/eurekaInternal")
    public String eurekaInternal(){
        System.out.println("Make call to eureka's internal service ...");
        return restTemplate.getForObject("http://eureka.tools:8080/testInternal", String.class);
    }
    
        @RequestMapping("/eurekaExternal")
    public String eurekaExternal(){
        System.out.println("Make call to eureka's external service ...");
        return restTemplate.getForObject("http://eureka.tools:8080/testExternal", String.class);
    }
}
