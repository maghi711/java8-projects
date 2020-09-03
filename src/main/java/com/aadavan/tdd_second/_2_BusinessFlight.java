package com.aadavan.tdd_second;

public class _2_BusinessFlight extends _2_Flight {

    public _2_BusinessFlight(String id) {
        super(id);
    }

    @Override
    public boolean addPassenger(_2_Passenger passenger) {
        if (passenger.isVip()) {
            return passengerList.add(passenger);
        }
        return false;
    }

    @Override
    public boolean removePassenger(_2_Passenger passenger) {
        return false;
    }
}
