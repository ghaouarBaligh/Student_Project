package com.example.demo;

import java.time.LocalDate;
import java.util.Random;
import java.util.UUID;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.demo.Repository.PaymentRepository;
import com.example.demo.Repository.StudentRepository;
import com.example.demo.entities.Payment;
import com.example.demo.entities.PaymentStatus;
import com.example.demo.entities.PaymentType;
import com.example.demo.entities.Student;

@SpringBootApplication
public class BalighProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(BalighProjectApplication.class, args);
	}
	
	
	@Bean
	CommandLineRunner commandLineRunner(StudentRepository studentRepository,
	                                    PaymentRepository paymentRepository) {
	    return args -> {
	        studentRepository.save(Student.builder()
	                .id(UUID.randomUUID().toString())
	                .firstName("Mohamed")
	                .code("11")
	                .programId("SDIA")
	                .build());
	        studentRepository.save(Student.builder()
	                .id(UUID.randomUUID().toString())
	                .firstName("Aymen")
	                .code("22")
	                .programId("SDIA")
	                .build());
	        studentRepository.save(Student.builder()
	                .id(UUID.randomUUID().toString())
	                .firstName("Baligh")
	                .code("33")
	                .programId("GLSID")
	                .build());
	        studentRepository.save(Student.builder()
	                .id(UUID.randomUUID().toString())
	                .firstName("Ahmed")
	                .code("44")
	                .programId("BDCC")
	                .build());
	        
	        
	        PaymentType[] paymentTypes = PaymentType.values();
	        Random random = new Random();
	        studentRepository.findAll().forEach(st->{
	        	for(int i=0;i<10;i++) {
	        		int index = random.nextInt(paymentTypes.length);
	        		Payment payment = Payment.builder()
	        				.amount(1000+(int)(Math.random()*20000))
	        				.type(paymentTypes[index])
	        				.status(PaymentStatus.CREATED)
	        				.date(LocalDate.now())
	        				.student(st)
	        				.build();
	        		paymentRepository.save(payment);
	        	}
	        });
	    };
	}


}
