package com.techelevator.dao;

import com.techelevator.model.Pet;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcPetDao implements PetDao {
    // This may potentially need to be connected via DataSource, if the connection fails
    private JdbcTemplate jdbcTemplate;

    //public JdbcPetDao(JdbcTemplate jdbcTemplate) {
       // this.jdbcTemplate = jdbcTemplate;
   // }

    public JdbcPetDao(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void createPet(Pet pet, Principal principal) {
        String sql= "INSERT INTO pets (name, breed, birth_year, gender, temperament, size, spayed_neutered, image) VALUES (?,?,?,?,?,?,?,?)";
        jdbcTemplate.update(sql,pet.getName(),pet.getBreed(),pet.getBirthYear(),pet.getGender(),pet.getTemperament(), pet.getSize(), pet.getSpayedNeutered(),pet.getImage());

        String sql2= "INSERT INTO user_pet (pet_id, user_id) VALUES ((SELECT pet_id FROM pets WHERE name = ?)," +
                " (SELECT user_id FROM users WHERE username = ?))";
        jdbcTemplate.update(sql2, pet.getName(), principal.getName());
    }

    @Override
    public List<Pet> getAllPets() {
        List<Pet> petList = new ArrayList<>();
        String sql= "SELECT name, breed, birth_year, gender, temperament, size, spayed_neutered, image FROM pets ";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()){
            petList.add(mapRowtoPet(results));
        }
        return petList;
    }

    @Override
    public List<Pet> getPetsByUserId(long userId) {
        List<Pet> petsByUserId = new ArrayList<>();
        String sql= "SELECT pet_id, name, breed, birth_year, gender, temperament, size, spayed_neutered, image FROM pets WHERE pet_id IN (SELECT pet_id FROM user_pet WHERE user_id= ?) ";
        SqlRowSet results= jdbcTemplate.queryForRowSet(sql,userId);
        while (results.next()){
            petsByUserId.add(mapRowtoPet(results));
        }
        return petsByUserId;

    }

    @Override
    public Pet getPetById(long petId) {
        Pet petById = new Pet();
        String sql= "SELECT name, breed, birth_year, gender, temperament, size, spayed_neutered, image FROM pets WHERE pet_id = ? ";
        SqlRowSet result= jdbcTemplate.queryForRowSet(sql,petId);
        if (result.next()){
            petById=mapRowtoPet(result);
        }
        return petById;
    }

    @Override
    public void updatePet(Pet pet) {
        String sql= "UPDATE pets SET name = ?, breed = ?, birth_year =?, gender =?, temperament =?, size =?, spayed_neutered = ? WHERE pet_id = ? ";
        jdbcTemplate.update(sql, pet.getName(), pet.getBreed(), pet.getBirthYear(),
                pet.getGender(), pet.getTemperament(), pet.getSize(), pet.getSpayedNeutered(), pet.getPetId());
    }

    @Override
    public void removePet(long petId) {

        String sql6 = "DELETE FROM request " +
                "WHERE mate_id = ? AND status_id != 5 OR mate_id = ? AND status_id != 4";
        jdbcTemplate.update(sql6, petId, petId);

        String sql3 = "UPDATE play_dates SET status_id = 4, host_pet_id = null "+
                "WHERE host_pet_id = ? " +
                "AND status_id != 5 AND meeting_date >= CURRENT_DATE ";
        jdbcTemplate.update(sql3,petId);

        String sql4= "UPDATE play_dates SET status_id = 4, mate_pet_id = null  "+
                "WHERE mate_pet_id = ? " +
                "AND status_id != 5 AND meeting_date >= CURRENT_DATE ";
        jdbcTemplate.update(sql4,petId);

        String sql5= "DELETE from request WHERE mate_id = ?";
        jdbcTemplate.update(sql5,petId);

        String sqlString= "DELETE FROM user_pet WHERE pet_id =?";
        jdbcTemplate.update(sqlString,petId);

        String sql= "DELETE FROM pets WHERE pet_id =?";
        jdbcTemplate.update(sql,petId);
    }



    private Pet mapRowtoPet(SqlRowSet results){
        Pet pet = new Pet();
        pet.setPetId(results.getLong("pet_id"));
        pet.setName(results.getString("name"));
        pet.setBreed(results.getString("breed"));
        pet.setBirthYear(results.getInt("birth_year"));
        pet.setGender(results.getString("gender"));
        pet.setTemperament(results.getString("temperament"));
        pet.setSize(results.getString("size"));
        pet.setSpayedNeutered(results.getString("spayed_neutered"));
        pet.setImage(results.getString("image"));

        return pet;
    }
}
