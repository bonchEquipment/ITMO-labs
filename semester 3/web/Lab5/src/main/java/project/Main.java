package project;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import project.entities.Click;
import project.repositories.ClickRepository;

import java.time.LocalTime;

@SpringBootApplication
public class Main {


    @Autowired
    ClickRepository clickRepository;

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }


    @Bean
    CommandLineRunner commandLineRunner(){
        return args -> {
        };
    }
}
