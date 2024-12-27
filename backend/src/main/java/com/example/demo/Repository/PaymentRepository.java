package com.example.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Payment;
import com.example.demo.entities.PaymentStatus;
import com.example.demo.entities.PaymentType;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

	List<Payment> findByStudentCode(String code);
	List<Payment> findByStatus(PaymentStatus status);
	List<Payment> findByType(PaymentType type);
}
