package com.petStore.PetStore.controller;

import com.petStore.PetStore.model.Pet;
import com.petStore.PetStore.repository.PetRepositoryImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
public class PetController{

    @Autowired
    private PetRepositoryImpl petRepositoryImpl;

    @GetMapping("/findAll")
    public List<Pet> findAll(){
        return petRepositoryImpl.findAll();
    }

    @RequestMapping("/register")
    @ResponseBody
    public String register(@RequestParam("name") String name, @RequestParam("type") String type, @RequestParam("gender") String gender,
                           @RequestParam("price") Double price, @RequestParam("color") String color){

        Pet pet = new Pet(name, type, gender, price, color);

        if(petRepositoryImpl.register(pet) >= 1){
            return "Item Added Successfully";
        }else{
            return "Something went wrong !";
        }
    }

    @RequestMapping("/findById")
    @ResponseBody
    public Optional<Pet> findById(@RequestParam("id") int id){
        return petRepositoryImpl.findById(id);
    }

    @RequestMapping("/deleteById")
    @ResponseBody
    public String deleteById(@RequestParam("id") int id){

        if(petRepositoryImpl.deleteById(id) >= 1){
            return "Item Deleted Successfully";
        }else{
            return "Something went wrong !";
        }
    }

    @RequestMapping("/update")
    @ResponseBody
    public String update(@RequestParam("id") int id, @RequestParam("color") String color){

        Optional<Pet> findPet;
        findPet = findById(id);

        findPet.ifPresent(pet -> {
                    pet.setColor(color);
                });

        log.info("[ COLOR ] : ");
        log.info("OUTPUT : {}", findPet);

        Pet obj = findPet.get();

        log.info("OUTPUT : {}", obj);

        if(petRepositoryImpl.update(obj) >= 1){
            return "Item Updated Successfully";
        }else{
            return "Something went wrong !";
        }
    }





}
