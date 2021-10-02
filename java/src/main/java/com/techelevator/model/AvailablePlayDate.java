package com.techelevator.model;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.Date;

public class AvailablePlayDate {

    private long playDateId;
    private long hostPetId;
    private String locationStreetAddress;
    private String locationCity;
    private long locationZipcode;
    private Date meetingDate;
    private LocalTime startTime;
    private long duration;
    private String[] mateDescription;
    private String mateSize;
    private long statusId;
    private String name;
    private String breed;
    private String temperament;
    private String size;
    private String spayedNeutered;

    public AvailablePlayDate() {
    }

    public AvailablePlayDate(long playDateId, long hostPetId, String locationStreetAddress, String locationCity,
                             long locationZipcode, Date meetingDate, LocalTime startTime, long duration,
                             String[] mateDescription, String mateSize, long statusId, String name, String breed,
                             String temperament, String size, String spayedNeutered) {
        this.playDateId = playDateId;
        this.hostPetId = hostPetId;
        this.locationStreetAddress = locationStreetAddress;
        this.locationCity = locationCity;
        this.locationZipcode = locationZipcode;
        this.meetingDate = meetingDate;
        this.startTime = startTime;
        this.duration = duration;
        this.mateDescription = mateDescription;
        this.mateSize = mateSize;
        this.statusId = statusId;
        this.name = name;
        this.breed = breed;
        this.temperament = temperament;
        this.size = size;
        this.spayedNeutered = spayedNeutered;
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

    public Date getMeetingDate() {
        return meetingDate;
    }

    public void setMeetingDate(Date meetingDate) {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getTemperament() {
        return temperament;
    }

    public void setTemperament(String temperament) {
        this.temperament = temperament;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getSpayedNeutered() {
        return spayedNeutered;
    }

    public void setSpayedNeutered(String spayedNeutered) {
        this.spayedNeutered = spayedNeutered;
    }

    @Override
    public String toString() {
        return "AvailablePlayDate{" +
                "playDateId=" + playDateId +
                ", hostPetId=" + hostPetId +
                ", locationStreetAddress='" + locationStreetAddress + '\'' +
                ", locationCity='" + locationCity + '\'' +
                ", locationZipcode=" + locationZipcode +
                ", meetingDate=" + meetingDate +
                ", startTime=" + startTime +
                ", duration=" + duration +
                ", mateDescription=" + Arrays.toString(mateDescription) +
                ", mateSize='" + mateSize + '\'' +
                ", statusId=" + statusId +
                ", name='" + name + '\'' +
                ", breed='" + breed + '\'' +
                ", temperament='" + temperament + '\'' +
                ", size='" + size + '\'' +
                ", spayedNeutered='" + spayedNeutered + '\'' +
                '}';
    }
}
