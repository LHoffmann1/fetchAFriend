package com.techelevator.dao;


import com.techelevator.model.Pet;
import com.techelevator.model.PlayDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class JdbcPlayDateDao implements PlayDateDao{

    private JdbcTemplate jdbcTemplate;

    public JdbcPlayDateDao(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }
    @Override
    public List<PlayDate> getAllPlayDates() {
        List<PlayDate> playDateList = new ArrayList<>();
        String sql = "SELECT play_date_id, host_pet_id, mate_pet_id, location_street_address, location_city, location_zipcode, meeting_date, " +
                "start_time, duration, mate_description, mate_size, status_id FROM play_dates";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            playDateList.add(mapToRowSet(results));
        }
        return playDateList;
    }

    //play dates where the user is the host
    @Override
    public List<PlayDate> getPlayDatesByUserId(long userId) {
        List<PlayDate> playDateListByUserId= new ArrayList<>();
        String sql= "SELECT play_date_id, host_pet_id, mate_pet_id, location_street_address, location_city, location_zipcode, meeting_date, " +
                "start_time, duration, mate_description, mate_size, status_id FROM play_dates " +
                "WHERE host_pet_id IN (SELECT pet_id FROM user_pet WHERE user_id = ?)";
        SqlRowSet results= jdbcTemplate.queryForRowSet(sql,userId);
        while (results.next()) {
            playDateListByUserId.add(mapToRowSet(results));
        }
        return playDateListByUserId;
    }

    @Override
    public void createPlayDate(PlayDate playDate) {
        String sql = "INSERT INTO play_dates (host_pet_id, location_street_address, location_city, location_zipcode, " +
                "meeting_date, start_time, duration, mate_description, mate_size, status_id) VALUES(?,?,?,?,?,?,?,?,?,?)";
        System.out.println(playDate);
        jdbcTemplate.update(sql, playDate.getHostPetId(), playDate.getLocationStreetAddress(),
                playDate.getLocationCity(), playDate.getLocationZipcode(), playDate.getMeetingDate(),
                playDate.getStartTime(), playDate.getDuration(), Arrays.toString(playDate.getMateDescription()), playDate.getMateSize(), 1);
    }

    @Override
    public LocalDate getCurrentDate() {
        return LocalDate.now();
    }

    @Override
    public void updatePlayDate(long playDateId, long matePetId) {
        String sql = "UPDATE play_dates SET status_id = 3, mate_pet_id = ? WHERE play_date_id = ?";
        jdbcTemplate.update(sql, matePetId, playDateId);

    }

    @Override
    public List<PlayDate> getConfirmedPlayDates(long userId) {
        List<PlayDate> playDateList = new ArrayList<>();
        String sql= "SELECT play_date_id, host_pet_id, mate_pet_id, location_street_address, location_city, location_zipcode, meeting_date, " +
                "start_time, duration, mate_description, mate_size, status_id FROM play_dates " +
                "WHERE host_pet_id IN (SELECT pet_id FROM user_pet WHERE user_id = ?) " +
                "AND status_id = 3 AND meeting_date >= CURRENT_DATE " +
                "OR mate_pet_id IN (SELECT pet_id FROM user_pet WHERE user_id = ?) " +
                "AND status_id = 3 AND meeting_date >= CURRENT_DATE " +
                "ORDER BY meeting_date ASC";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId, userId);
        while (results.next()) {
            playDateList.add(mapToRowSet(results));
        }
        return playDateList;

    }

    @Override
    public List<PlayDate> getPlayDatesPendingHostApproval(long userId) {
        List<PlayDate> pendingPlayDatesList = new ArrayList<>();
        String sql= "SELECT play_dates.play_date_id, host_pet_id, mate_pet_id, location_street_address, location_city, location_zipcode, meeting_date, " +
                "start_time, duration, mate_description, mate_size, play_dates.status_id FROM play_dates " +
                "JOIN request ON play_dates.play_date_id= request.play_date_id " +
                "JOIN pets ON request.mate_id= pets.pet_id " +
                "JOIN user_pet ON pets.pet_id= user_pet.pet_id WHERE user_id = ? " +
                "AND request.status_id = 2 AND meeting_date >= CURRENT_DATE " +
                "ORDER BY meeting_date ASC";


        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);

        while(results.next()) {
            pendingPlayDatesList.add(mapToRowSet(results));
        }

        return pendingPlayDatesList;
    }

    @Override
    public void cancelPlayDate(long playDateId) {
        String sql = "UPDATE play_dates SET status_id = 4 WHERE play_date_id = ?";
        jdbcTemplate.update(sql, playDateId);
    }


    private PlayDate mapToRowSet(SqlRowSet results) {
        PlayDate playDate = new PlayDate();
        playDate.setPlayDateId(results.getLong("play_date_id"));
        playDate.setHostPetId(results.getLong("host_pet_id"));
        playDate.setMatePetId(results.getLong("mate_pet_id"));
        playDate.setLocationStreetAddress(results.getString("location_street_address"));
        playDate.setLocationCity(results.getString("location_city"));
        playDate.setLocationZipcode(results.getLong("location_zipcode"));
        playDate.setMeetingDate(results.getDate("meeting_date").toLocalDate());
        playDate.setStartTime(results.getTime("start_time").toLocalTime());
        playDate.setDuration(results.getLong("duration"));
        playDate.setMateDescription(results.getString("mate_description").split(","));
        playDate.setMateSize(results.getString("mate_size"));
        playDate.setStatusId(results.getLong("status_id"));

        return playDate;
    }

    private PlayDate mapToConfirmed(SqlRowSet results) {
        PlayDate playDate = new PlayDate();
        playDate.setPlayDateId(results.getLong("play_date_id"));
        playDate.setHostPetId(results.getLong("host_pet_id"));
        playDate.setMatePetId(results.getLong("mate_pet_id"));
        playDate.setLocationStreetAddress(results.getString("location_street_address"));
        playDate.setLocationCity(results.getString("location_city"));
        playDate.setLocationZipcode(results.getLong("location_zipcode"));
        playDate.setMeetingDate(results.getDate("meeting_date").toLocalDate());
        playDate.setStartTime(results.getTime("time").toLocalTime());
        playDate.setDuration(results.getLong("duration"));
        playDate.setMateDescription(results.getString("mate_description").split(","));
        playDate.setMateSize(results.getString("mate_size"));
        playDate.setStatusId(results.getLong("status_id"));


        return playDate;
    }


}
