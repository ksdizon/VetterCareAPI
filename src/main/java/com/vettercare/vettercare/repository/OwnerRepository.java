package com.vettercare.vettercare.repository;

import com.vettercare.vettercare.model.owner.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Long> {
    Optional<Owner> findByOwnerFirstNameAndOwnerLastNameAndAddress(String ownerFirstName, String ownerLastName, String address);
}
