package com.techelevator.model;

public class Request {

    private long requestId;
    private long playDateId;
    private long statusId;
    private long mateId;

    private long hostPetId;
    private long petId;
    private String mateName;
    private String mateBreed;
    private int mateBirthYear;
    private String mateGender;
    private String mateTemperament;
    private String mateSize;
    private String mateSpayedNeutered;
    private PlayDate playdate;

    public Request(){

    }

    public Request(long requestId, long playDateId, long statusId, long mateId, long hostPetId, long petId, String mateName, String mateBreed, int mateBirthYear, String mateGender, String mateTemperament, String mateSize, String mateSpayedNeutered) {
        this.requestId = requestId;
        this.playDateId = playDateId;
        this.statusId = statusId;
        this.mateId = mateId;
        this.hostPetId = hostPetId;
        this.petId = petId;
        this.mateName = mateName;
        this.mateBreed = mateBreed;
        this.mateBirthYear = mateBirthYear;
        this.mateGender = mateGender;
        this.mateTemperament = mateTemperament;
        this.mateSize = mateSize;
        this.mateSpayedNeutered = mateSpayedNeutered;
    }

    public PlayDate getPlaydate() {
        return playdate;
    }

    public void setPlaydate(PlayDate playdate) {
        this.playdate = playdate;
    }

    public long getRequestId() {
        return requestId;
    }

    public void setRequestId(long requestId) {
        this.requestId = requestId;
    }

    public long getPlayDateId() {
        return playDateId;
    }

    public void setPlayDateId(long playDateId) {
        this.playDateId = playDateId;
    }

    public long getStatusId() {
        return statusId;
    }

    public void setStatusId(long statusId) {
        this.statusId = statusId;
    }

    public long getMateId() {
        return mateId;
    }

    public void setMateId(long mateId) {
        this.mateId = mateId;
    }

    public long getHostPetId() {
        return hostPetId;
    }

    public void setHostPetId(long hostPetId) {
        this.hostPetId = hostPetId;
    }

    public long getPetId() {
        return petId;
    }

    public void setPetId(long petId) {
        this.petId = petId;
    }

    public String getMateName() {
        return mateName;
    }

    public void setMateName(String mateName) {
        this.mateName = mateName;
    }

    public String getMateBreed() {
        return mateBreed;
    }

    public void setMateBreed(String mateBreed) {
        this.mateBreed = mateBreed;
    }

    public int getMateBirthYear() {
        return mateBirthYear;
    }

    public void setMateBirthYear(int mateBirthYear) {
        this.mateBirthYear = mateBirthYear;
    }

    public String getMateGender() {
        return mateGender;
    }

    public void setMateGender(String mateGender) {
        this.mateGender = mateGender;
    }

    public String getMateTemperament() {
        return mateTemperament;
    }

    public void setMateTemperament(String mateTemperament) {
        this.mateTemperament = mateTemperament;
    }

    public String getMateSize() {
        return mateSize;
    }

    public void setMateSize(String mateSize) {
        this.mateSize = mateSize;
    }

    public String getMateSpayedNeutered() {
        return mateSpayedNeutered;
    }

    public void setMateSpayedNeutered(String mateSpayedNeutered) {
        this.mateSpayedNeutered = mateSpayedNeutered;
    }

    @Override
    public String toString() {
        return "Request{" +
                "requestId=" + requestId +
                ", playDateId=" + playDateId +
                ", statusId=" + statusId +
                ", mateId=" + mateId +
                ", hostPetId=" + hostPetId +
                ", petId=" + petId +
                ", mateName='" + mateName + '\'' +
                ", mateBreed='" + mateBreed + '\'' +
                ", mateBirthYear=" + mateBirthYear +
                ", mateGender='" + mateGender + '\'' +
                ", mateTemperament='" + mateTemperament + '\'' +
                ", mateSize='" + mateSize + '\'' +
                ", mateSpayedNeutered='" + mateSpayedNeutered + '\'' +
                '}';
    }
}
