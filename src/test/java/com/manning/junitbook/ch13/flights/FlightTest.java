package com.manning.junitbook.ch13.flights;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FlightTest {
    // 정상적으로 생성되었다면 항공편 객체가 null이 아니다.
    @Test
    public void testFlightCreation() {
        Flight flight = new Flight("AA123", 100);
        assertNotNull(flight);
    }

    // 객체가 정상적으로 생성되지 않을때 예외를 던지는지 검증한다.
    @Test
    public void testInvalidFlightNumber() {
        assertThrows(RuntimeException.class, () -> {
            Flight flight = new Flight("AA12", 100);
        });
    }
}
