package com.webapp.controller;

import java.util.List;

import org.hibernate.internal.build.AllowSysOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.webapp.dto.RegistrationDto;
import com.webapp.entity.Registration;
import com.webapp.service.EmailService;
import com.webapp.service.RegistrationService;

@Controller
public class RegistrationController {
	
	@Autowired
	private RegistrationService registrationService;
	
	@Autowired
	private EmailService emailService;
	//Handler Methods
	//http://localhost:8080/view_registration
	
	@RequestMapping("/view_registration")
	public String viewRegistrationPage() {
		
		return "new_registration"; //Request Dispatcher
	}
	// this is approach 1 of reading the data
//	@RequestMapping("/saveReg")
//	public String saveRegistration(Registration registration) {
//		registrationService.createRegistration(registration);
//		return "new_registration";
//	}
	
	//what is @ModelAttribute annotation
	//Model = ModelMap
	@RequestMapping("/saveReg")
	public String saveRegistration(@ModelAttribute Registration registration, Model model) {
		registrationService.createRegistration(registration);
		emailService.sendEmail(registration.getEmail(), "Test", "Welcome");
		model.addAttribute("msg", "Record is saved!");
		return "new_registration";
	}
	
	//this is the 2nd way of reading the data 
//	@RequestMapping("/saveReg")
//	public String saveRegistration(@RequestParam("firstName") String firstName,
//			@RequestParam("lastName") String lastName,
//			@RequestParam("email") String email,
//			@RequestParam("mobile") long mobile) {
//		Registration reg = new Registration();
//		reg.setFirstName(firstName);
//		reg.setLastName(lastName);
//		reg.setEmail(email);
//		reg.setMobile(mobile);
//		registrationService.createRegistration(reg);
//		return "new_registration";
//	}
	
	//3rd way of reading the data from view layer
//	@RequestMapping("/saveReg")
//	public String saveRegistration(RegistrationDto registrationDto, ModelMap model) {
//		Registration reg = new Registration();
//		reg.setFirstName(registrationDto.getFirstName());
//		reg.setLastName(registrationDto.getLastName());
//		reg.setEmail(registrationDto.getEmail());
//		reg.setMobile(registrationDto.getMobile());
//		registrationService.createRegistration(reg);
//		model.addAttribute("msg", "Record is saved!");
//		return "new_registration";
//	}
	
	//http://localhost:8080/all-registrations
	@RequestMapping("all-registrations")
	public String getAllRegistrations(Model model){
		List<Registration> reg = registrationService.getRegistrations();
		model.addAttribute("registrations", reg);
		return "all-registrations";
	}
	
	//http://localhost:8080/delete-registration?id=1   //Query parameter
	@RequestMapping("/delete-registration")
	public String deleteRegistration(@RequestParam long id, Model model) {
		registrationService.deleteRegistration(id);
		List<Registration> reg = registrationService.getRegistrations();
		model.addAttribute("registrations", reg);
		return "all-registrations";
	}
	
	@RequestMapping("/update-registration")
	public String updateRegistration(@RequestParam long id, Model model) {
		Registration registration = registrationService.getRegistrationById(id);
		model.addAttribute("registration",registration);
		return "update_registration";
	}
	
	@RequestMapping("/updateRegRecord")
	public String updateRegRecord(RegistrationDto dto, Model model) {
		Registration registration = new Registration();
		registration.setId(dto.getId());
		registration.setFirstName(dto.getFirstName());
		registration.setLastName(dto.getLastName());
		registration.setEmail(dto.getEmail());
		registration.setMobile(dto.getMobile());		
		registrationService.updateRegRecord(registration);		
		List<Registration> reg = registrationService.getRegistrations();
		model.addAttribute("registrations", reg);
		return "all-registrations";
		
	}
}
