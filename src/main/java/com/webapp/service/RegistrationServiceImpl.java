package com.webapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webapp.entity.Registration;
import com.webapp.repository.RegistrationRepository;

@Service
public class RegistrationServiceImpl implements RegistrationService{

	@Autowired
	private RegistrationRepository registrationRepository;
	
	@Override
	public void createRegistration(Registration registration) {
		registrationRepository.save(registration);
		
	}

	@Override
	public List<Registration> getRegistrations() {
		List<Registration> registrations = registrationRepository.findAll();
		return registrations;
	}

	@Override
	public void deleteRegistration(long id) {
		registrationRepository.deleteById(id);
		
	}

	@Override
	public Registration getRegistrationById(long id) {
		Registration registration = registrationRepository.findById(id).get();
		return registration;
	}

	@Override
	public void updateRegRecord(Registration registration) {
		registrationRepository.save(registration);
		
	}

}
