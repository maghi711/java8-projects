package com.aadavan.tdd_second;

import java.util.ArrayList;
import java.util.List;

public abstract class _2_Flight {

    private String id;
    protected List<_2_Passenger> passengerList = new ArrayList<>();

    public _2_Flight(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public List<_2_Passenger> getPassengerList() {
        return passengerList;
    }

    public abstract boolean addPassenger(_2_Passenger passenger);
    public abstract boolean removePassenger(_2_Passenger passenger);
}
