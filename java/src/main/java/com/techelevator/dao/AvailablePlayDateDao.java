package com.techelevator.dao;

import com.techelevator.model.AvailablePlayDate;
import com.techelevator.model.PlayDate;

import java.security.Principal;
import java.util.List;

public interface AvailablePlayDateDao {

    List<AvailablePlayDate> getAvailablePlayDates(long userId);
    AvailablePlayDate getAvailablePlayDateByPlayDateId(long playDateId);


}
