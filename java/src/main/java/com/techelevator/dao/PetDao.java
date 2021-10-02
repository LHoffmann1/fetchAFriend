package com.techelevator.dao;

import com.techelevator.model.Pet;

import java.security.Principal;
import java.util.List;

public interface PetDao {

    void createPet(Pet pet, Principal principal);

    List<Pet> getAllPets();

    List<Pet> getPetsByUserId(long userId);

    Pet getPetById(long petId);

    void updatePet(Pet pet);

    void removePet(long petId);

}
