package com.vettercare.vettercare.model.owner;

import com.vettercare.vettercare.model.patient.Patient;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "owners")
public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ownerId;

    private String ownerFirstName;
    private String ownerLastName;
    private String address;

    @OneToMany(mappedBy = "owner")
    private List<Patient> pets;

    public Owner(String ownerFirstName, String ownerLastName, String address, List<Patient> pets) {
        this.ownerFirstName = ownerFirstName;
        this.ownerLastName = ownerLastName;
        this.address = address;
        this.pets = pets;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public String getOwnerFirstName() {
        return ownerFirstName;
    }

    public void setOwnerFirstName(String ownerFirstName) {
        this.ownerFirstName = ownerFirstName;
    }

    public String getOwnerLastName() {
        return ownerLastName;
    }

    public void setOwnerLastName(String ownerLastName) {
        this.ownerLastName = ownerLastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Patient> getPets() {
        return pets;
    }

    public void setPets(List<Patient> pets) {
        this.pets = pets;
    }
}
