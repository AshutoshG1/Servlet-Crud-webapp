package com.webapp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.webapp.dto.RegistrationDto;
import com.webapp.entity.Registration;
import com.webapp.exception.ResourceNotFound;
import com.webapp.repository.RegistrationRepository;

@RestController
@RequestMapping("/api/reg")
public class RestRegistrationController {

	@Autowired
	private RegistrationRepository registrationRepository;
	
	//http://localhost:8080/api/reg
	@GetMapping
	public List<Registration> getAllRegistrations(){
		List<Registration> registrations = registrationRepository.findAll();
		return registrations;
		
	}
	
	@PostMapping 
	public ResponseEntity<Registration> createRegistration(@RequestBody Registration registration) {
		Registration savedRegistration = registrationRepository.save(registration);
		return new ResponseEntity<>(savedRegistration, HttpStatus.CREATED);
	}
	
	//http://localhost:8080/api/reg/12
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteRegistration(@PathVariable long id){
		registrationRepository.deleteById(id);
		return new ResponseEntity<>("Deleted!!", HttpStatus.OK);
	}
	
	//http://localhost:8080/api/reg?id=1
	@PutMapping
	public ResponseEntity<Registration> updateRegistration(
			@RequestParam long id,
			@RequestBody RegistrationDto dto
			){
		Registration registration = registrationRepository.findById(id).get();
		registration.setFirstName(dto.getFirstName());
		registration.setLastName(dto.getLastName());
		registration.setEmail(dto.getEmail());
		registration.setMobile(dto.getMobile());
		Registration savedRegistration = registrationRepository.save(registration);
		return new ResponseEntity<>(savedRegistration, HttpStatus.OK);
		
	}
	
	//http://localhost:8080/api/reg/1
	@GetMapping("/{id}")
	public ResponseEntity<?> getRegistrationById(@PathVariable long id){
		try {
			Optional<Registration> byId = registrationRepository.findById(id);
			Registration registration = byId.get();
			return new ResponseEntity<>(registration, HttpStatus.OK);
			
			
		} catch (Exception e) {
			return new ResponseEntity<>("Registration not found with id :"+id, HttpStatus.OK);
		}
	}
	
}
