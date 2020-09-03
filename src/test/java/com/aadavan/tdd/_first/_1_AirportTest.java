package com.aadavan.tdd._first;

/*
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
*/

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class _1_AirportTest {

    @Test
    public void testAirport() {
        _1_Flight economyFlight = new _1_Flight("1", "Economy");
        _1_Flight businessFlight = new _1_Flight("2", "Business");

        _1_Passenger aadavan = new _1_Passenger("Aadavan", true);
        _1_Passenger swetha = new _1_Passenger("Swetha", false);

        assertEquals(true, businessFlight.addPassenger(aadavan));
        assertEquals(false, businessFlight.removePassenger(aadavan));
        assertEquals(true, economyFlight.addPassenger(swetha));
        assertEquals(true, economyFlight.removePassenger(swetha));

    }
}
