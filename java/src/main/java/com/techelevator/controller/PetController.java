package com.techelevator.controller;

import com.techelevator.dao.AvailablePlayDateDao;
import com.techelevator.dao.PetDao;
import com.techelevator.dao.PlayDateDao;
import com.techelevator.dao.RequestDao;
import com.techelevator.model.AvailablePlayDate;
import com.techelevator.model.Pet;
import com.techelevator.model.PlayDate;
import com.techelevator.model.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

@PreAuthorize("isAuthenticated()")
@RestController
@CrossOrigin
@RequestMapping("/pet")
public class PetController {

    @Autowired
    private PetDao petDao;
    @Autowired
    private PlayDateDao playDateDao;
    @Autowired
    private AvailablePlayDateDao availablePlayDateDao;
    @Autowired
    private RequestDao requestDao;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/addPuppy")
    public void createPet(Principal principal, @RequestBody Pet newDog){
        petDao.createPet(newDog, principal);
    }

    @GetMapping("/allPets")
    public List<Pet> getAllPets(){
        return petDao.getAllPets();
    }

    @GetMapping("/userPets/{id}")
    public List<Pet> getPetsByUser(@PathVariable long id){
        return petDao.getPetsByUserId(id);
    }

    @GetMapping("/{id}")
    public Pet getPetById(@PathVariable("id") long petId){
        return petDao.getPetById(petId);
    }

    @GetMapping("/createPlayDate")
    public LocalDate getCurrentDate(){
        return playDateDao.getCurrentDate();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/createPlayDate")
    public void createPlayDate(@RequestBody PlayDate playDate){
        playDateDao.createPlayDate(playDate);
    }


    @GetMapping("/allPlayDates/")
    public List<PlayDate> getAllPlayDates (){
        return playDateDao.getAllPlayDates();
    }

    @GetMapping("/allPlayDates/{id}")
    public List<PlayDate> getPlayDatesByUserid (@PathVariable("id") long userId){
        return playDateDao.getPlayDatesByUserId(userId);
    }

    @GetMapping("/allPlayDates/available/{userId}")
    public List<AvailablePlayDate> getAvailablePlayDates(@PathVariable long userId) {
        return availablePlayDateDao.getAvailablePlayDates(userId);
    }

    @GetMapping("/playDate/{id}")
    public AvailablePlayDate getAvailablePlayDateByPlayDateId(@PathVariable("id") long playDateId){
        return availablePlayDateDao.getAvailablePlayDateByPlayDateId(playDateId);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/request")
    public void createRequest(@RequestBody Request request){
        requestDao.createRequest(request);
    }

    @GetMapping("/myRequest/{id}")
    public List<Request> getRequestsByUserId(@PathVariable("id") long userId){
        return requestDao.getAllRequestsByUserId(userId);
    }

    @GetMapping("/request/{id}")
    public List<Request> getRequestsByMateId(@PathVariable("id") long matePetId){
        return requestDao.getAllRequestsByMateId(matePetId);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/request/{playDateId}/{mateId}")
    public void updateRequest(@PathVariable long playDateId, @PathVariable long mateId) {
        playDateDao.updatePlayDate(playDateId, mateId);
        requestDao.updateRequest(playDateId, mateId);
    }

    @GetMapping("/playDates/{userId}")
    public List<PlayDate> getConfirmedPlayDates(@PathVariable long userId) {
        return playDateDao.getConfirmedPlayDates(userId);
    }

    @GetMapping("/playDatesPending/{userId}")
    public List<Request> getPlayDatesPendingHostApproval(@PathVariable long userId) {
        return requestDao.getPlayDatesPendingHostApproval(userId);
    }

    @PutMapping("/pupdate")
    @ResponseStatus(HttpStatus.OK)
    public void updatePet(@RequestBody Pet pet){
        petDao.updatePet(pet);
    }

    @DeleteMapping("/pupdate/{petId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removePet(@PathVariable long petId){
        petDao.removePet(petId);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/playDates/cancel/{playDateId}")
    public void cancelPlayDate(@PathVariable long playDateId) {
        playDateDao.cancelPlayDate(playDateId);
    }






}
