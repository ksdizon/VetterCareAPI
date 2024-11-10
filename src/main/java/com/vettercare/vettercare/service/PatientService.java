package com.vettercare.vettercare.service;

import com.vettercare.vettercare.model.patient.Patient;
import com.vettercare.vettercare.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    @Autowired
    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<Patient> getPatients(String species, String breed) {
        if (species != null && breed != null) {
            return patientRepository.findBySpeciesAndBreed(species, breed);
        } else if (species != null) {
            return patientRepository.findBySpecies(species);
        } else if (breed != null) {
            return patientRepository.findByBreed(breed);
        }
        return patientRepository.findAll();
    }

    public Optional<Patient> getPatientById(Long id) {
        return patientRepository.findById(id);
    }

    public void addPatient(Patient patient) {
        patientRepository.save(patient);
    }
}
