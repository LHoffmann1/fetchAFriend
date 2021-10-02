package com.techelevator.model;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Date;

public class PlayDate {

    private long playDateId;
    private long hostPetId;
    private long matePetId;
    private String locationStreetAddress;
    private String locationCity;
    private long locationZipcode;
    private LocalDate meetingDate;
    private LocalTime startTime;
    private long duration;
    private String[] mateDescription;
    private String mateSize;
    private long statusId;
    private String userPetName;


    public PlayDate(){

    }

    public PlayDate(long playDateId, long hostPetId, long matePetId, String locationStreetAddress, String locationCity, long locationZipcode, LocalDate meetingDate, LocalTime startTime, long duration, String[] mateDescription, String mateSize, long statusId, String userPetName) {
        this.playDateId = playDateId;
        this.hostPetId = hostPetId;
        this.matePetId = matePetId;
        this.locationStreetAddress = locationStreetAddress;
        this.locationCity = locationCity;
        this.locationZipcode = locationZipcode;
        this.meetingDate = meetingDate;
        this.startTime = startTime;
        this.duration = duration;
        this.mateDescription = mateDescription;
        this.mateSize = mateSize;
        this.statusId = statusId;
        this.userPetName = userPetName;
    }

    public String getUserPetName() {
        return userPetName;
    }

    public void setUserPetName(String userPetName) {
        this.userPetName = userPetName;
    }

    public long getPlayDateId() {
        return playDateId;
    }

    public void setPlayDateId(long playDateId) {
        this.playDateId = playDateId;
    }

    public long getHostPetId() {
        return hostPetId;
    }

    public void setHostPetId(long hostPetId) {
        this.hostPetId = hostPetId;
    }

    public long getMatePetId() {
        return matePetId;
    }

    public void setMatePetId(long matePetId) {
        this.matePetId = matePetId;
    }

    public String getLocationStreetAddress() {
        return locationStreetAddress;
    }

    public void setLocationStreetAddress(String locationStreetAddress) {
        this.locationStreetAddress = locationStreetAddress;
    }

    public String getLocationCity() {
        return locationCity;
    }

    public void setLocationCity(String locationCity) {
        this.locationCity = locationCity;
    }

    public long getLocationZipcode() {
        return locationZipcode;
    }

    public void setLocationZipcode(long locationZipcode) {
        this.locationZipcode = locationZipcode;
    }

    public LocalDate getMeetingDate() {
        return meetingDate;
    }

    public void setMeetingDate(LocalDate meetingDate) {
        this.meetingDate = meetingDate;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public String[] getMateDescription() {
        return mateDescription;
    }

    public void setMateDescription(String[] mateDescription) {
        this.mateDescription = mateDescription;
    }

    public String getMateSize() {
        return mateSize;
    }

    public void setMateSize(String mateSize) {
        this.mateSize = mateSize;
    }

    public long getStatusId() {
        return statusId;
    }

    public void setStatusId(long statusId) {
        this.statusId = statusId;
    }

    @Override
    public String toString() {
        return "PlayDate{" +
                "playDateId=" + playDateId +
                ", hostPetId=" + hostPetId +
                ", matePetId=" + matePetId +
                ", locationStreetAddress='" + locationStreetAddress + '\'' +
                ", locationCity='" + locationCity + '\'' +
                ", locationZipcode=" + locationZipcode +
                ", meetingDate=" + meetingDate +
                ", startTime=" + startTime +
                ", duration=" + duration +
                ", mateDescription=" + Arrays.toString(mateDescription) +
                ", mateSize='" + mateSize + '\'' +
                ", statusId=" + statusId +
                '}';
    }


    public String timeToString() {
        return "'" + startTime + "'";

    }
}
