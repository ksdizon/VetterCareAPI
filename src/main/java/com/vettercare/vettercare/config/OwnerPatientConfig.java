package com.vettercare.vettercare.config;

import com.vettercare.vettercare.model.owner.Owner;
import com.vettercare.vettercare.model.patient.Patient;
import com.vettercare.vettercare.service.OwnerService;
import com.vettercare.vettercare.service.PatientService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OwnerPatientConfig {

    private final OwnerService ownerService;
    private final PatientService patientService;

    @Autowired
    public OwnerPatientConfig(OwnerService ownerService, PatientService patientService) {
        this.ownerService = ownerService;
        this.patientService = patientService;
    }

    @Bean
    public CommandLineRunner dataLoader() {
        return args -> {
            Owner owner1 = new Owner("Ken Shamrock", "Dizon", "Angeles City");
            ownerService.addOwner(owner1);

            Patient patient1 = new Patient(owner1, "Choby", "Cat", "Puspin", 3, 4.0, "Female");
            patientService.addPatient(patient1);

            Owner owner2 = new Owner("Raine Chastine", "Nolasco", "Angeles City");
            ownerService.addOwner(owner2);

            Patient patient2 = new Patient(owner2, "Cara", "Cat", "Puspin", 3, 3.0, "Female");
            patientService.addPatient(patient2);

            Patient patient3 = new Patient(owner2, "Borgi", "Dog", "Aspin", 1, 20.0, "Male");
            patientService.addPatient(patient3);

            Patient patient4 = new Patient(owner2, "Bragi", "Dog", "Aspin", 1, 15.0, "Male");
            patientService.addPatient(patient4);
        };
    }
}
