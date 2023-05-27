package com.gridnine.testing;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.service.FlightService;
import com.gridnine.testing.service.impl.FlightServiceImpl;
import com.gridnine.testing.util.FlightBuilder;

import java.util.List;

public class Main {

    private static final FlightService flightService = new FlightServiceImpl();

    public static void main(String[] args) {
        List<Flight> flightList;
        List<Flight> flightsDepartureTimeAfterNowList;
        List<Flight> flightsWrongList;
        List<Flight> flightsWaitingTimeMoreThanHoursList;
        flightList = FlightBuilder.createFlights();

        System.out.println("______________All Flights______________");
        for (Flight flight : flightList) {
            System.out.println("Полет:" + flight);
        }

        flightsDepartureTimeAfterNowList = flightService.getFlightsDepartureTimeAfterNow(flightList); //Убрать рейсы которые уже улетели (1 задание из списка правил)
        flightsWrongList = flightService.getWrongFlights(flightList); //Убрать неправильные рейсы (2 задание из списка правил)
        flightsWaitingTimeMoreThanHoursList = flightService.getFlightsWaitingTimeMoreThanHours(flightList, 2); //Убрать рейсы, пересадка которая длится более 2х часов (3 задание из списка правил)

        System.out.println("\n________Filtered flights rule 1________");
        for (Flight flight : flightsDepartureTimeAfterNowList) {
            System.out.println("Полет:" + flight);
        }

        System.out.println("\n________Filtered flights rule 2________");
        for (Flight flight : flightsWrongList) {
            System.out.println("Полет:" + flight);
        }

        System.out.println("\n________Filtered flights rule 3________");
        for (Flight flight : flightsWaitingTimeMoreThanHoursList) {
            System.out.println("Полет:" + flight);
        }
    }
}