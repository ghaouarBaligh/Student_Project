package com.example.demo.web;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.Repository.PaymentRepository;
import com.example.demo.Repository.StudentRepository;
import com.example.demo.dtos.NewPaymentDTO;
import com.example.demo.entities.Payment;
import com.example.demo.entities.PaymentStatus;
import com.example.demo.entities.PaymentType;
import com.example.demo.entities.Student;
import com.example.demo.services.PaymentService;

import io.swagger.v3.oas.models.Paths;
import io.swagger.v3.oas.models.media.MediaType;

@RestController
//@CrossOrigin("*")
public class PaymentRestController {

	private StudentRepository studentRepository;
	private PaymentRepository paymentRepository;
	private PaymentService paymentService;
	
	public PaymentRestController(StudentRepository studentRepository, PaymentRepository paymentRepository, PaymentService paymentService) {
		this.studentRepository=studentRepository;
		this.paymentRepository=paymentRepository;
		this.paymentService=paymentService;
	}
	
	@GetMapping(path = "/payments")
	public List<Payment> allPayments(){
		return paymentRepository.findAll();
	}
	
	@GetMapping(path = "/students/{code}/payments")
	public List<Payment> paymentByStudent(@PathVariable String code){
		return paymentRepository.findByStudentCode(code);
	}
	
	@GetMapping(path = "/paymentsByStatus")
	public List<Payment> paymentByStatus(@RequestParam PaymentStatus status){
		return paymentRepository.findByStatus(status);
	}
	
	@GetMapping(path = "/paymentsByType")
	public List<Payment> paymentByType(@RequestParam PaymentType type){
		return paymentRepository.findByType(type);
	}
	
	@GetMapping(path = "/paymentsById/{id}")
	public Payment getPaymentById(@PathVariable Long id) {
		return paymentRepository.findById(id).get();
	}
	
	@GetMapping(path = "/students")
	public List<Student> allStudents(){
		return studentRepository.findAll();
	}
	
	@GetMapping(path = "/paymentsByCode/{code}")
	public Student getStudentByCode(@PathVariable String code) {
		return studentRepository.findByCode(code);
	}

	@GetMapping(path = "/studentsByProgramId")
	public List<Student> getStudentByProgramId(@RequestParam String programId) {
		return studentRepository.findByProgramId(programId);
	}
	
	@PutMapping(path= "/payments/{id}")
	public Payment updatePaymentStatus(@RequestParam PaymentStatus status,@PathVariable Long id) {
		return paymentService.updatePaymentStatus(status, id);
	}
	
	@PostMapping(path= "/newPayments", consumes=org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE)
	public Payment savePayment(@RequestParam("file") MultipartFile file, NewPaymentDTO newPaymentDTO) throws IOException {
		return this.paymentService.savePayment(file, newPaymentDTO);
	}
	
	@GetMapping(path = "/paymentFile/{paymentId}",produces = org.springframework.http.MediaType.APPLICATION_PDF_VALUE)
	public byte[] getPaymentFile(@PathVariable Long paymentId) throws IOException {
		return paymentService.getPaymentFile(paymentId);
	}
	
	
	
}
