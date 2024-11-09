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
        this.ownerRepository.save(owner);
    }

}
