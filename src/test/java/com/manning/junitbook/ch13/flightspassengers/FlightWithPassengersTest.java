package com.manning.junitbook.ch13.flightspassengers;

import com.manning.junitbook.ch13.flights.Flight;
import com.manning.junitbook.ch13.passengers.Passenger;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class FlightWithPassengersTest {
    // 1. 한 개의 좌석을 가진 항공편 객체를 생성
    private Flight flight = new Flight("AA123", 1);

    @Test
    public void testPassengerJoinsFlight() {
        // 승객을 생성하고
        Passenger passenger = new Passenger("123-45-6789", "John Smith", "US");
        // 항공편을 생성한다.
        Flight flight = new Flight("AA123", 100);
        // 승객관점에서 항공편을 추가한다.
        passenger.joinFlight(flight);
        // 승객의 항공편이 앞서 정의한 항공편인지 검증
        assertEquals(flight, passenger.getFlight());
        // 해당 항공편의 승객수가 현재 한 명인지 검증한다.
        assertEquals(1, flight.getNumberOfPassengers());
    }

    @Test
    public void testAddRemovePassengers() throws IOException {
        // 2. 승객 객체를 생성
        Passenger passenger = new Passenger("124-56-7890", "Michael Johnson", "US");

        // 항공편에 승객을 추가
        flight.addPassenger(passenger);
        assertEquals(1, flight.getNumberOfPassengers());
        // 승객관점에서 항공편이 추가되었는지 검증한다.
        assertEquals(flight, passenger.getFlight());

        // 항공편에서 승객을 삭제
        flight.removePassenger(passenger);
        assertEquals(0, flight.getNumberOfPassengers());
        // 승객관점에서 항공편이 삭제되었는지 검증한다.
        assertEquals(null, passenger.getFlight());
    }

    @Test
    public void testNumberOfSeats() {
        // 5. 승객 객체를 생성하고 항공편에 추가
        Passenger passenger1 = new Passenger("124-56-7890", "Michael Johnson", "US");

        flight.addPassenger(passenger1);
        // 6. 항공편의 승객 수가 허용 좌석 수 1인지 확인
        assertEquals(1, flight.getNumberOfPassengers());

        // 7. 항공편의 허용 좌석수를 초과하도록 승객 객체를 생성
        Passenger passenger2 = new Passenger("127-23-7991", "John Smith", "GB");
        // 8. 항공편에 승객을 추가하여 승객 수가 좌석 수를 초과하면 예외가 발생하는지 확인
        assertThrows(RuntimeException.class, () -> {
            flight.addPassenger((passenger2));
        });
    }
}
