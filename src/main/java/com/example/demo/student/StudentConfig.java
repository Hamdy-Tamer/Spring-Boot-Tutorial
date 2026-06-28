package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

import java.time.LocalDate;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository){
        return args -> {
            Student hamdy = new Student("Hamdy", "hamdytamer253@gmail.com", LocalDate.of(2004, 8, 29));
            Student farah = new Student("Farah", "farahahmed690@gmail.com", LocalDate.of(2001, 5, 7));
            Student tamer = new Student("Tamer", "tamerhamdy470@gmail.com", LocalDate.of(1974, 10, 30));


            repository.saveAll(
                    List.of(hamdy, farah, tamer)
            );
        };
    }
}