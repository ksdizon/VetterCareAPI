package com.vettercare.vettercare.repository;

import com.vettercare.vettercare.model.patient.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

        List<Patient> findBySpecies(String species) ;

        List<Patient> findByBreed(String breed);

        List<Patient> findBySpeciesAndBreed(String species, String breed);
}
