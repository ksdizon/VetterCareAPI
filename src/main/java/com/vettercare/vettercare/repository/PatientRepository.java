package com.vettercare.vettercare.repository;

import com.vettercare.vettercare.model.patient.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    List<Patient> filterBySpecies(String species) ;

    List<Patient> filterByBreed(String breed);

    List<Patient> filterBySpeciesAndBreed(String species, String breed);
}
