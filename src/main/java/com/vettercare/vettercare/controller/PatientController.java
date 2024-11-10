package com.vettercare.vettercare.controller;

import com.vettercare.vettercare.dto.PatientDto;
import com.vettercare.vettercare.model.patient.Patient;
import com.vettercare.vettercare.service.PatientService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping
    public ResponseEntity<List<PatientDto>> getPatients(
            @RequestParam(required = false) String species,
            @RequestParam(required = false) String breed
    ) {
        List<PatientDto> patients = patientService.getPatients(species, breed);

        return new ResponseEntity<>(patients, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientDto> getPatientById(@PathVariable Long id) {
        PatientDto patient = patientService.getPatientById(id);

        try {
            return new ResponseEntity<>(patient, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<HttpStatus> updatePatientRecord(
//            @PathVariable Long id,
//            @RequestBody Patient patient) {
//
//        try {
//            patientService.updatePatientRecord(id, patient);
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        } catch (RuntimeException e) {
//            return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
//        }
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deletePatient(@PathVariable Long id) {
        try {
            patientService.deletePatient(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
