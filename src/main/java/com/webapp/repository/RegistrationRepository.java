package com.webapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webapp.entity.Registration;

public interface RegistrationRepository extends JpaRepository<Registration, Long> {

}
