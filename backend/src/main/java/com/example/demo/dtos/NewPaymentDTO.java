package com.example.demo.dtos;

import java.time.LocalDate;

import com.example.demo.entities.PaymentType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class NewPaymentDTO {
	
	private double amount;
	private PaymentType type;
	private LocalDate date;
	private String studentCode;

}
