package com.manning.junitbook.ch13.passengers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PassengerTest {
    // 올바른 파라미터를 사용하여 승객을 생성했는지 검증한다.
    // 정상적으로 생성되었다면 승객 객체가 null이 아닐 것이다.
    @Test
    public void testPassengerCreation() {
        Passenger passenger = new Passenger("123-45-6789", "John Smith", "US");
        assertNotNull(passenger);
    }

    // countryCode 파라미터가 유효하지 않다면 Passenger 생성자가 예외를 던지는지 검증한다.
    @Test
    public void testInvalidCountryCode() {
        assertThrows(RuntimeException.class, () -> {
            Passenger passenger = new Passenger("900-45-6789", "John Smith", "GJ");
        });
    }

    // toString() 메서드가 올바르게 동작하는지 검증한다.
    @Test
    public void testPassengerToString() {
        Passenger passenger = new Passenger("123-45-6789", "John Smith", "US");
        assertEquals("Passenger John Smith with identifier: 123-45-6789 from US", passenger.toString());
    }
}
