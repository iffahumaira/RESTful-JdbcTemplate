package com.petStore.PetStore.repository;

import com.petStore.PetStore.model.Pet;

import java.util.List;
import java.util.Optional;

public interface PetRepository {

    int register(Pet pet);
    int update(Pet pet);
    int deleteById(Integer id);

    List<Pet> findAll();

    Optional<Pet> findById(Integer id);

}
