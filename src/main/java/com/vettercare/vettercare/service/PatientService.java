package com.vettercare.vettercare.service;

import com.vettercare.vettercare.dto.PatientDto;
import com.vettercare.vettercare.model.patient.Patient;
import com.vettercare.vettercare.repository.OwnerRepository;
import com.vettercare.vettercare.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PatientService {

    @Autowired
    private final PatientRepository patientRepository;

    @Autowired
    private final OwnerRepository ownerRepository;

    public PatientService(PatientRepository patientRepository, OwnerRepository ownerRepository) {
        this.patientRepository = patientRepository;
        this.ownerRepository = ownerRepository;
    }

    private PatientDto convertToDto(Patient patient) {
        return new PatientDto(
                patient.getPatientId(),
                patient.getOwner().getOwnerId(),
                patient.getName(),
                patient.getSpecies(),
                patient.getBreed(),
                patient.getAge(),
                patient.getWeight(),
                patient.getGender()
        );
    }

    public List<PatientDto> getPatients(String species, String breed) {
        List<Patient> patients;

        if (species != null && breed != null) {
            patients = patientRepository.findBySpeciesAndBreed(species, breed);
        } else if (species != null) {
            patients = patientRepository.findBySpecies(species);
        } else if (breed != null) {
            patients = patientRepository.findByBreed(breed);
        } else {
            patients = patientRepository.findAll();
        }

        return patients.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public PatientDto getPatientById(Long id) {
        if (patientRepository.findById(id).isPresent()) {
            return convertToDto(patientRepository.findById(id).get());
        } else {
            throw new RuntimeException("Patient with id " + id + " does not exist");
        }
    }

    public void addPatient(Patient patient) {
        patientRepository.save(patient);
    }

    public void deletePatient(Long id) {
        if (patientRepository.existsById(id)) {
            patientRepository.deleteById(id);
        } else {
            throw new RuntimeException("Patient with id " + id + " does not exists");
        }
    }
}
