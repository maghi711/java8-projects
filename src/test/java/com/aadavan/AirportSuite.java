package com.aadavan;

import com.aadavan.tdd._first._1_AirportTest;
import com.aadavan.tdd_second._2_AirportTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses(
        {
                _1_AirportTest.class,
                _2_AirportTest.class
        }
)
public class AirportSuite {
}
