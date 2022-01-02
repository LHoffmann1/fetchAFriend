package com.techelevator.dao;

import com.techelevator.model.Request;

import java.util.List;

public interface RequestDao {

    void createRequest(Request request);
    List<Request> getAllRequestsByUserId(long userId);
    void updateRequest(long playDateId, long matePateId);
    List<Request> getPlayDatesPendingHostApproval(long userId);

    //List<Request> getAllRequestsByMateId(long matePetId);

}
