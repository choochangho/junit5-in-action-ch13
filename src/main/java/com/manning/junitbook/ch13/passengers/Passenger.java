package com.manning.junitbook.ch13.passengers;

import com.manning.junitbook.ch13.flights.Flight;

import java.util.Arrays;
import java.util.Locale;

public class Passenger {
    // Passenger 클래스는 identifier, name, countryCode 필드를 가지고 있다.
    private String identifier;
    private String name;
    private String countryCode;
    private Flight flight;

    // Passenger 생성자는 국가 코드가 유효한지 확인하고, 국가 코드가 유효한 경우에만
    // identifier, name, countryCode 필드를 초기화한다.
    public Passenger(String identifier, String name, String countryCode) {
        if(!Arrays.asList(Locale.getISOCountries()).contains(countryCode)) {
            throw new RuntimeException("국가 코드가 적절하지 않습니다.");
        }
        this.identifier = identifier;
        this.name = name;
        this.countryCode = countryCode;
    }

    // identifier, name, countryCode 에 대한 getter 메서드를 정의한다.
    public String getIdentifier() {
        return identifier;
    }

    public String getName() {
        return name;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public Flight getFlight() {
        return flight;
    }

    // 승객 정보를 나타낼 수 있도록 toString 메서드를 재정의한다.
    @Override
    public String toString() {
        return "Passenger " + getName() + " with identifier: " + getIdentifier() + " from " + getCountryCode();
    }
}
