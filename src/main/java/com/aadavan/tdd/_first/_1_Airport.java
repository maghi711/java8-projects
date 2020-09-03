package com.aadavan.tdd._first;

import com.aadavan.lambdas.consumer.Consumer;

public class _1_Airport {
    public static void main(String[] args) {
        _1_Flight economyFlight = new _1_Flight("1", "Economy");
        _1_Flight businessFlight = new _1_Flight("2", "Business");

        _1_Passenger aadavan = new _1_Passenger("Aadavan", true);
        _1_Passenger swetha = new _1_Passenger("Swetha", false);

        businessFlight.addPassenger(aadavan);
        economyFlight.addPassenger(swetha);

        System.out.println("Business Flight passengers list");
        businessFlight.getPassengerList().forEach(System.out::println);
        economyFlight.getPassengerList().forEach(System.out::println);
    }
}
