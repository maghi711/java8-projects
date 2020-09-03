package com.aadavan.tdd._first;

import java.rmi.UnexpectedException;
import java.util.ArrayList;
import java.util.List;

public class _1_Flight {

    private String id;
    private String flightType;
    private List<_1_Passenger> passengerList = new ArrayList<>();

    public _1_Flight(String id, String flightType) {
        this.id = id;
        this.flightType = flightType;
    }

    public String getId() {
        return id;
    }

    public String getFlightType() {
        return flightType;
    }

    public List<_1_Passenger> getPassengerList() {
        return passengerList;
    }

    public boolean addPassenger(_1_Passenger passenger) {
        switch (flightType) {
            case "Economy":
                return passengerList.add(passenger);
            case "Business":
                if (passenger.isVip()) {
                    return passengerList.add(passenger);
                }
                return false;
            default:
                throw new RuntimeException("Unknown type: " + flightType);
        }
    }

    public boolean removePassenger(_1_Passenger passenger) {
        switch (flightType) {
            case "Economy":
                if (!passenger.isVip()) {
                    return passengerList.remove(passenger);
                }
            case "Business":
                return false;
            default:
                throw new RuntimeException("Unknown type: " + flightType);
        }
    }
}
