package com.manning.junitbook.ch13.flights;

import com.manning.junitbook.ch13.passengers.Passenger;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Flight {
    // Flight 클래스는 flightNumber, seats, passengers 필드를 가지고 있다.
    private String flightNumber;
    private int seats;
    private Set<Passenger> passengers = new HashSet<>();

    // 항공편명이 적절한지를 판단할 수 있는 정규식
    private static String flightNumberRegex = "^[A-Z]{2}\\d{3,4}$";
    // Pattern 객체는 모든 항공편에 대해 동일하므로 정적으로 사용할 수 있다.
    private static Pattern pattern = Pattern.compile(flightNumberRegex);

    public Flight(String flightNumber, int seats) {
        // Matcher 클래스는 정규식 패턴과 입력받은 flightNumber 변수 간에 매치가 되는지 안되는지 검증하는데 사용한다.
        Matcher matcher = pattern.matcher(flightNumber);
        // 정규식 패턴과 매치되지 않으면 예외가 발생한다.
        if(!matcher.matches()) {
            throw new RuntimeException("항공편명이 적절하지 않습니다.");
        }
        // 두 개의 인스턴스 필드에 파라미터로 전달 받은 값을 할당한다.
        this.flightNumber = flightNumber;
        this.seats = seats;
    }

    // 항공편명과 승객 번호에 대한 게터 메서드를 정의한다.
    public String getFlightNumber() {
        return flightNumber;
    }

    public int getNumberOfPassengers() {
        return passengers.size();
    }

    // 승객 수가 좌석 수를 초과하는 경우 예외가 발생한다.
    // 승객 데이터를 성공적으로 추가했을 때는 true를 반환
    // 승객이 이미 존재한다면 false를 반환
    // https://docs.oracle.com/javase/8/docs/api/java/util/HashSet.html#add-E-
    public boolean addPassenger(Passenger passenger) {
        if(getNumberOfPassengers() >= seats) {
            throw new RuntimeException(getFlightNumber() + " 항공편에 좌석이 부족합니다.");
        }
        passenger.setFlight(this);
        return passengers.add(passenger);
    }

    // 현재 항공편에서 승객 객체를 삭제한다.
    // 성공적으로 삭제했을 때는 true를 반환
    // 승객이 존재하지 않는다면 false를 반환
    // https://docs.oracle.com/javase/8/docs/api/java/util/HashSet.html#remove-java.lang.Object-
    public boolean removePassenger(Passenger passenger) {
        passenger.setFlight(null);
        return passengers.remove(passenger);
    }

}
