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

    public void joinFlight(Flight flight) {
        // 승객의 이전 항공편 정보를 가져온 다음
        // 이전 항공편 정보가 존재한다면 이전 항공편에서 승객을 삭제한다.
        // 삭제가 실패한다면 예외를 던진다.
        Flight previousFlight = this.flight;
        if(null != previousFlight) {
            if(!previousFlight.removePassenger(this)) {
                throw new RuntimeException("승객을 삭제할 수 없습니다.");
            }
        }
        // 세터 메서드로 승객 객체에 항공편 정보를 설정한다.
        setFlight(flight);
        // 항공편 정보가 null이 아니면 항공편에도 승객 정보를 추가한다.
        // 승객 정보가 성공적으로 추가되지 않는다면 예외를 던진다.
        if(null != flight) {
            if(!flight.addPassenger(this)) {
                throw new RuntimeException("승객을 추가할 수 없습니다.");
            }
        }
    }

    // 승객 정보를 나타낼 수 있도록 toString 메서드를 재정의한다.
    @Override
    public String toString() {
        return "Passenger " + getName() + " with identifier: " + getIdentifier() + " from " + getCountryCode();
    }
}
