package com.techelevator.dao;

import com.techelevator.model.PlayDate;
import com.techelevator.model.Request;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
@Component
public class JdbcRequestDao implements RequestDao {

    private JdbcTemplate jdbcTemplate;
    public JdbcRequestDao(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void createRequest(Request request) {
        Request newRequest = new Request();
        String sql= "INSERT INTO request (play_date_id,status_id,mate_id) VALUES (?,?,?) ";
        jdbcTemplate.update(sql, request.getPlayDateId(),2,request.getMateId());
    }

    //for a host to see all pending requests and the mate dog details
    @Override
    public List<Request> getAllRequestsByUserId(long userId) {
        List<Request> requestsByUserId = new ArrayList<>();
        String sql= "SELECT play_dates.play_date_id, request.mate_id, play_dates.meeting_date, play_dates.start_time, play_dates.duration, play_dates.location_street_address, " +
                "play_dates.host_pet_id, pets.pet_id, pets.name, pets.breed, pets.birth_year, pets.gender, pets.temperament, pets.size, pets.spayed_neutered, pets.image FROM request " +
                "JOIN play_dates ON request.play_date_id= play_dates.play_date_id JOIN pets ON request.mate_id= pets.pet_id " +
                "JOIN user_pet ON play_dates.host_pet_id=user_pet.pet_id WHERE user_id = ? AND request.status_id = 2 " +
                "GROUP BY play_dates.play_date_id, pets.pet_id, request.mate_id " +
                "ORDER BY meeting_date ASC";

        SqlRowSet results= jdbcTemplate.queryForRowSet(sql,userId);
        while (results.next()){
            requestsByUserId.add(mapRowToSet(results, mapRowToPlayDate(results)));
        }
        System.out.println(requestsByUserId);
        return requestsByUserId;
    }



    //retrieve list of all playdates user's pet has applied for but not yet confirmed
    @Override
    public List<Request> getPlayDatesPendingHostApproval(long userId) {
        List<Request> pendingPlayDatesList = new ArrayList<>();
        String sql= "SELECT play_dates.play_date_id, request.mate_id, play_dates.meeting_date, play_dates.start_time, play_dates.duration, play_dates.location_street_address, " +
                "play_dates.host_pet_id, pets.pet_id, pets.name, pets.breed, pets.birth_year, pets.gender, pets.temperament, pets.size, pets.spayed_neutered FROM request " +
                "JOIN play_dates ON request.play_date_id= play_dates.play_date_id JOIN pets ON request.mate_id= pets.pet_id " +
                "JOIN user_pet ON pets.pet_id=user_pet.pet_id WHERE user_id = ? AND request.status_id = 2 " +
                "AND play_dates.meeting_date >= CURRENT_DATE GROUP BY play_dates.play_date_id, pets.pet_id, request.mate_id " +
                "ORDER BY meeting_date ASC";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);

        while(results.next()) {
            pendingPlayDatesList.add(mapToRowSet2(results, mapRowToPlayDate(results)));
        }

        return pendingPlayDatesList;
    }

    //update to confirm approved request and decline all other requests for same playdate
    @Override
    public void updateRequest(long playDateId, long mateId) {
        String sql = "UPDATE request SET status_id = 3, mate_id = ? " +
                "WHERE play_date_id = ?";
        jdbcTemplate.update(sql, mateId, playDateId);

        String sql2 = "UPDATE request SET status_id = 5 WHERE play_date_id = ? and mate_id != ?";
        jdbcTemplate.update(sql, playDateId, mateId);

    }

    private Request mapRowToSet(SqlRowSet results, PlayDate date) {
        Request request = new Request();
  //      PlayDate date = new PlayDate();
        request.setPlayDateId(results.getLong("play_date_id"));
        request.setMateId(results.getLong("mate_id"));
        request.setHostPetId(results.getLong("host_pet_id"));
        request.setPetId(results.getLong("pet_id"));
        request.setMateName(results.getString("name"));
        request.setMateBreed(results.getString("breed"));
        request.setMateBirthYear(results.getInt("birth_year"));
        request.setMateGender(results.getString("gender"));
        request.setMateTemperament(results.getString("temperament"));
        request.setMateSize(results.getString("size"));
        request.setMateSpayedNeutered(results.getString("spayed_neutered"));
        request.setMateImage((results.getString("image")));

        String sql = "SELECT name FROM pets WHERE pet_id = ?";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, request.getHostPetId());
        if(result.next()){
            request.setHostName(result.getString("name"));
        }

        request.setPlaydate(date);
        return request;
    }

    private PlayDate mapRowToPlayDate(SqlRowSet results){
        PlayDate date = new PlayDate();
        date.setMeetingDate(results.getDate("meeting_date").toLocalDate());
        date.setStartTime(results.getTime("start_time").toLocalTime());
        date.setDuration(results.getLong("duration"));
        date.setLocationStreetAddress(results.getString("location_street_address"));
        return date;
    }

    private Request mapToRowSet2(SqlRowSet results, PlayDate date) {
        Request request = new Request();
        request.setPlayDateId(results.getLong("play_date_id"));
        request.setHostPetId(results.getLong("host_pet_id"));
        request.setMateId(results.getLong("mate_id"));
        request.setMateSize(results.getString("size"));

        //new chunk start
        String sql = "SELECT name,image FROM pets WHERE pet_id = ?";
        SqlRowSet result1 = jdbcTemplate.queryForRowSet(sql, request.getHostPetId());
        if(result1.next()){
            request.setHostName(result1.getString("name"));
            request.setMateImage(result1.getString("image"));
        }
        String sql2 = "SELECT name FROM pets WHERE pet_id=?";
        SqlRowSet result2 = jdbcTemplate.queryForRowSet(sql2, request.getMateId());
        if(result2.next()){
            request.setMateName(result2.getString("name"));
        }
        //new chunk end
        request.setPlaydate(date);
        return request;
    }
}





// for nothing
    /*@Override
    public List<Request> getAllRequestsByMateId(long userId) {
        List<Request> requestsByUserId = new ArrayList<>();
        String sql= "SELECT play_dates.play_date_id, request.mate_id, play_dates.meeting_date, play_dates.start_time, play_dates.duration, play_dates.location_street_address, " +
                "play_dates.host_pet_id, pets.pet_id, pets.name, pets.breed, pets.birth_year, pets.gender, pets.temperament, pets.size, pets.spayed_neutered FROM request " +
                "JOIN play_dates ON request.play_date_id= play_dates.play_date_id JOIN pets ON request.mate_id= pets.pet_id " +
                "JOIN user_pet ON pets.pet_id=user_pet.pet_id WHERE user_id = ? AND request.status_id = 2 " +
                "GROUP BY play_dates.play_date_id, pets.pet_id, request.mate_id " +
                "ORDER BY meeting_date ASC";

        SqlRowSet results= jdbcTemplate.queryForRowSet(sql,userId);
        while (results.next()){
            requestsByUserId.add(mapRowToSet(results, mapRowToPlayDate(results)));
        }
        System.out.println(requestsByUserId);
        return requestsByUserId;

    }*/


 /*"SELECT play_dates.play_date_id, host_pet_id, mate_pet_id, location_street_address, location_city, location_zipcode, meeting_date, " +
                "start_time, duration, mate_description, mate_size, play_dates.status_id FROM play_dates " +
                "JOIN request ON play_dates.play_date_id= request.play_date_id " +
                "JOIN pets ON request.mate_id= pets.pet_id " +
                "JOIN user_pet ON pets.pet_id= user_pet.pet_id WHERE user_id = ? " +
                "AND request.status_id = 2 AND meeting_date >= CURRENT_DATE " +
                "ORDER BY meeting_date ASC";*/
