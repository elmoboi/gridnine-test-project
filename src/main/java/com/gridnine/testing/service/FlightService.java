package com.gridnine.testing.service;

import com.gridnine.testing.model.Flight;

import java.util.List;

public interface FlightService {
    List<Flight> getFlightsDepartureTimeAfterNow(List<Flight> flights);

    List<Flight> getFlightsWaitingTimeLessThanHours(List<Flight> flights, Integer hour);

    List<Flight> getRightFlights(List<Flight> flights);

}
