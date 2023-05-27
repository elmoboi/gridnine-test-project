package com.gridnine.testing.service;

import com.gridnine.testing.model.Flight;

import java.util.List;

public interface FlightService {
    List<Flight> getFlightsDepartureTimeAfterNow(List<Flight> flights);
    List<Flight> getFlightsWaitingTimeMoreThanHours(List<Flight> flights, Integer hour);
    List<Flight> getWrongFlights(List<Flight> flights);
}
