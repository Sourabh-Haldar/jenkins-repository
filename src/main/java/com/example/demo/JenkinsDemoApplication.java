package com.example.demo;

import java.util.UUID;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.model.Student;
import com.example.demo.repo.StudentRepository;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;



@AllArgsConstructor
@SpringBootApplication
public class JenkinsDemoApplication {
	private StudentRepository studentRepository;
	public static void main(String[] args) {
		SpringApplication.run(JenkinsDemoApplication.class, args);
	}
	@PostConstruct
	public void init() {
		studentRepository.save(new Student("Sourabh","sourabh@gmail.com"));
		studentRepository.save(new Student("abcd","abcd@gmail.com"));
	}

}
