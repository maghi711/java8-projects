package com.aadavan.tdd_second;

import org.junit.*;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;

//import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class _2_AirportTest {

    @BeforeClass
    public static void beforeClass() {
        System.out.println(" Before the class " + _2_AirportTest.class.getCanonicalName());
    }

    @Before
    public void beforeEachTest() {
        System.out.println("Before each test.");
    }

    @Test(expected = NullPointerException.class)
    public void testNull() {
        String st = null;
        st.length();
    }

    @Test(timeout = 34)
    public void performanceCheck() {
        int a[] = {24, 34, 23};
        for (int i = 1; i < 1000000; i++) {
            a[0]=i;
            Arrays.sort(a);
        }
    }

    //@DisplayName("Test add and remove passenger")
    @Test
    public void testAirport() {
        _2_Flight economyFlight = new _2_EconomyFlight("1");
        _2_Flight businessFlight = new _2_BusinessFlight("2");

        _2_Passenger aadavan = new _2_Passenger("Aadavan", true);
        _2_Passenger swetha = new _2_Passenger("Swetha", false);

        assertEquals(true, economyFlight.addPassenger(swetha));
        assertEquals(true, economyFlight.removePassenger(swetha));
        assertEquals(false, businessFlight.addPassenger(swetha));
        assertEquals(false, businessFlight.removePassenger(swetha));

        assertEquals(true, economyFlight.addPassenger(aadavan));
        assertEquals(false, economyFlight.removePassenger(aadavan));
        assertEquals(true, businessFlight.addPassenger(aadavan));
        assertEquals(false, businessFlight.removePassenger(aadavan));

    }

    //@DisplayName("Test Economy Passenger in Business Flight")
    @Test
    public void testBusinessFlightEconomyPassenger() {
        _2_Flight businessFlight = new _2_BusinessFlight("2");
        _2_Passenger swetha = new _2_Passenger("Swetha", false);

        assertEquals(false, businessFlight.addPassenger(swetha));
        assertEquals(0, businessFlight.getPassengerList().size());
        assertEquals(false, businessFlight.removePassenger(swetha));
    }

    //@DisplayName("Test Vip Passenger in BusinessFlight")
    @Test
    public void testBusinessFlightVipPassenger() {
        _2_Flight businessFlight = new _2_BusinessFlight("2");
        _2_Passenger aadavan = new _2_Passenger("Aadavan", true);

        assertEquals(true, businessFlight.addPassenger(aadavan));
        assertEquals(1, businessFlight.getPassengerList().size());
        assertEquals(false, businessFlight.removePassenger(aadavan));
        assertEquals(1, businessFlight.getPassengerList().size());
    }

    //@DisplayName("Test Flight Id")
    @Test
    public void testFlightId() {
        _2_Flight economyFlight = new _2_EconomyFlight("1");
        assertEquals("1", economyFlight.getId());

        _2_Flight businessFlight = new _2_BusinessFlight("2");
        assertEquals("2", businessFlight.getId());

    }

    //@DisplayName("Test Passenger Name")
    @Test
    public void testPassengerName() {
        _2_Passenger aadavan = new _2_Passenger("Aadavan", true);
        _2_Passenger swetha = new _2_Passenger("Swetha", false);
        assertEquals("Aadavan", aadavan.getName());
        assertEquals("Swetha", swetha.getName());
    }

    @After
    public void afterEachTest() {
        System.out.println("After each test.");
    }

    @AfterClass
    public static void afterClass() {
        System.out.println("After class");
    }
}
