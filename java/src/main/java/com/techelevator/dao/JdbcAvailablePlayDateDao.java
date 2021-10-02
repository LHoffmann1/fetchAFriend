package com.techelevator.dao;

import com.techelevator.model.AvailablePlayDate;
import com.techelevator.model.PlayDate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.lang.reflect.Array;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
@Component
public class JdbcAvailablePlayDateDao implements AvailablePlayDateDao{

    private JdbcTemplate jdbcTemplate;
    public JdbcAvailablePlayDateDao(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Override
    public List<AvailablePlayDate> getAvailablePlayDates(long userId) {
        List<AvailablePlayDate> availablePlayDateList = new ArrayList<>();
        String sql = "SELECT play_date_id, host_pet_id, location_street_address, location_city, " +
                "location_zipcode, meeting_date, start_time, duration, mate_description, mate_size, " +
                "status_id, name, breed, temperament, size, spayed_neutered FROM play_dates JOIN " +
                "pets ON play_dates.host_pet_id = pets.pet_id JOIN user_pet ON play_dates.host_pet_id= user_pet.pet_id " +
                "WHERE status_id = 1 AND user_id!=? OR status_id = 2 AND user_id!=? ORDER BY meeting_date ASC";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId, userId);
        while (results.next()) {
            availablePlayDateList.add(mapToRowSet(results));
        }

        return availablePlayDateList;
    }

    @Override
    public AvailablePlayDate getAvailablePlayDateByPlayDateId(long playDateId) {
        AvailablePlayDate availablePlayDate = new AvailablePlayDate();
        String sql = "SELECT play_date_id, host_pet_id, location_street_address, location_city, " +
                "location_zipcode, meeting_date, start_time, duration, mate_description, mate_size, " +
                "status_id, name, breed, temperament, size, spayed_neutered FROM play_dates JOIN " +
                "pets ON play_dates.host_pet_id = pets.pet_id WHERE play_date_id =?";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, playDateId);
        if(results.next()){
            availablePlayDate = mapToRowSet(results);
        }
        return availablePlayDate;
    }

    //Not using for the pending play dates as we need mate_pet_id
//    @Override
//    public List<PlayDate> getPlayDatesPendingHostApproval(long userId) {
//        List<AvailablePlayDate> pendingPlayDatesList = new ArrayList<>();
//        String sql = "SELECT play_date_id, host_pet_id, location_street_address, location_city, " +
//                "location_zipcode, meeting_date, start_time, duration, mate_description, mate_size, " +
//                "status_id, name, breed, temperament, size, spayed_neutered FROM play_dates JOIN " +
//                "pets ON play_dates.host_pet_id = pets.pet_id WHERE status_id = 2 OR status_id = 2 ORDER BY meeting_date DESC";
//        return null;
//    }

    private AvailablePlayDate mapToRowSet(SqlRowSet results) {
        AvailablePlayDate availablePlayDate = new AvailablePlayDate();
        availablePlayDate.setPlayDateId(results.getLong("play_date_id"));
        availablePlayDate.setHostPetId(results.getLong("host_pet_id"));
        availablePlayDate.setLocationStreetAddress(results.getString("location_street_address"));
        availablePlayDate.setLocationCity(results.getString("location_city"));
        availablePlayDate.setLocationZipcode(results.getLong("location_zipcode"));
        availablePlayDate.setMeetingDate(results.getDate("meeting_date"));
        availablePlayDate.setStartTime(results.getTime("start_time").toLocalTime());
        availablePlayDate.setDuration(results.getLong("duration"));
        String description = (results.getString("mate_description"));
        description = description.substring(1,description.length()-1);
        availablePlayDate.setMateDescription(description.split(","));
        availablePlayDate.setMateSize(results.getString("mate_size"));
        availablePlayDate.setStatusId(results.getLong("status_id"));
        availablePlayDate.setName(results.getString("name"));
        availablePlayDate.setBreed(results.getString("breed"));
        availablePlayDate.setTemperament(results.getString("temperament"));
        availablePlayDate.setSize(results.getString("size"));
        availablePlayDate.setSpayedNeutered(results.getString("spayed_neutered"));

        return availablePlayDate;
    }

}
