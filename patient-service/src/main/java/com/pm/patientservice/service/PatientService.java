package com.pm.patientservice.service;

import com.pm.patientservice.model.Patient;
import com.pm.patientservice.repository.PatientRepository;
import org.springframework.stereotype.Service;

@Service
public class PatientService {
    private final PatientRepository patientRepository;


    //dependency injection
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;

    }
}


