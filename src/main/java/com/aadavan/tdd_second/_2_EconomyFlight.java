package com.aadavan.tdd_second;

public class _2_EconomyFlight extends _2_Flight {

    public _2_EconomyFlight(String id) {
        super(id);
    }

    @Override
    public boolean addPassenger(_2_Passenger passenger) {
        return passengerList.add(passenger);
    }

    @Override
    public boolean removePassenger(_2_Passenger passenger) {
        if (!passenger.isVip()) {
            return passengerList.remove(passenger);
        }
        return false;
    }
}
