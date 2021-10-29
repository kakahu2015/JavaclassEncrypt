package org.kakahu.test;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"org.kakahu"})
public class DemoApplication {

    public static void main(String[] args) {
        System.out.println("123");
        SpringApplication.run(DemoApplication.class, args);
    }

}
