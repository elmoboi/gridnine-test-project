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
        List<Flight> filteredList;
        List<Flight> filteredList1;
        List<Flight> filteredList2;
        flightList = FlightBuilder.createFlights();

        System.out.println("______________All Flights______________");
        for(Flight flight : flightList) {
            System.out.println("Полет:" + flight);
        }

        filteredList = flightService.getFlightsDepartureTimeAfterNow(flightList); //Убрать рейсы которые уже улетели (1 задание из списка правил)
        filteredList1 = flightService.getWrongFlights(flightList); //Убрать неправильные рейсы (2 задание из списка правил)
        filteredList2 = flightService.getFlightsWaitingTimeMoreThanHours(flightList,2); //Убрать рейсы, пересадка которая длится более 2х часов (3 задание из списка правил)

        System.out.println("\n________Filtered flights rule 1________");
        for(Flight flight : filteredList) {
            System.out.println("Полет:" + flight);
        }

        System.out.println("\n________Filtered flights rule 2________");
        for(Flight flight : filteredList1) {
            System.out.println("Полет:" + flight);
        }

        System.out.println("\n________Filtered flights rule 3________");
        for(Flight flight : filteredList2) {
            System.out.println("Полет:" + flight);
        }
    }
}