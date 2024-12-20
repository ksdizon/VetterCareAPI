package com.vettercare.vettercare.controller;

import com.vettercare.vettercare.model.owner.Owner;

import com.vettercare.vettercare.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("owners")
public class OwnerController {

    @Autowired
    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @GetMapping
    public ResponseEntity<List<Owner>> getOwners() {
        List<Owner> owners = this.ownerService.getOwners();

        if (!owners.isEmpty()) {
            return new ResponseEntity<>(owners, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // find by id
    @GetMapping("/{id}")
    public ResponseEntity<Owner> getOwnerById(@PathVariable Long id) {
        Optional<Owner> owner = this.ownerService.findOwnerById(id);

        if (owner.isPresent()) {
            return new ResponseEntity<>(owner.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public void addNewOwner(@RequestBody Owner owner) {
        ownerService.addOwner(owner);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HttpStatus> updateOwnerDetails(@PathVariable Long id, @RequestBody Owner owner) {
        try {
            ownerService.updateOwnerDetails(id, owner);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> removeOwner(@PathVariable Long id) {
        try {
            ownerService.deleteOwner(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
