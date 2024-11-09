package com.vettercare.vettercare.service;

import com.vettercare.vettercare.model.owner.Owner;
import com.vettercare.vettercare.repository.OwnerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.List;

@Service
public class OwnerService {

    private final OwnerRepository ownerRepository;

    @Autowired
    private OwnerService(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    public List<Owner> getOwners() {
        return this.ownerRepository.findAll();
    }

    public Optional<Owner> findOwnerById(Long id) {
        return this.ownerRepository.findById(id);
    }

    public void addOwner(Owner owner) {
        if (ownerRepository.findByOwnerFirstNameAndOwnerLastNameAndAddress(owner.getOwnerFirstName(),
                owner.getOwnerLastName(),
                owner.getAddress())
                .isEmpty()) {
            this.ownerRepository.save(owner);
        } else {
            System.out.println("Owner already exists");
        }
    }

    public void updateOwnerDetails(Long id, Owner owner) {
        Optional<Owner> ownerToUpdate = ownerRepository.findById(id);

        if (ownerToUpdate.isPresent()) {
            ownerToUpdate.get().setOwnerFirstName(owner.getOwnerFirstName());
            ownerToUpdate.get().setOwnerLastName(owner.getOwnerLastName());
            ownerToUpdate.get().setAddress(owner.getAddress());

            ownerRepository.save(ownerToUpdate.get());
        } else {
            throw new RuntimeException("Owner not found with id: " + id);
        }
    }

    public void deleteOwner(Long id) {
        if (ownerRepository.existsById(id)) {
            ownerRepository.deleteById(id);
        } else {
            throw new RuntimeException("Owner not found with id: " + id);
        }
    }
}
