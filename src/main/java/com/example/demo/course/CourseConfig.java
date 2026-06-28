package com.example.demo.course;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class CourseConfig {
    @Bean
    CommandLineRunner courseCommandLineRunner(CourseRepository repository){
        return args -> {
            Course java = new Course("Java Backend Programming", "JAVA-101");
            Course spring = new Course("Political Science", "POLT-102");
            repository.saveAll(
                    List.of(java, spring));
        };
    }
}