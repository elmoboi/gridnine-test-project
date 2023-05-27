package com.gridnine.testing.service.impl;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.service.FlightService;
import com.gridnine.testing.util.BinaryFlightSearch;

import java.util.*;

public class FlightServiceImpl implements FlightService {
    private final BinaryFlightSearch binaryFlightSearch = new BinaryFlightSearch();
    @Override
    public List<Flight> getFlightsDepartureTimeAfterNow(List<Flight> flights) {
        List<Flight> filteredFlights = new ArrayList<>();
        for (Flight flight : flights) {
            if (binaryFlightSearch.hasFirstSegmentDepartureTimeAfterOrEqualToDateTime(flight)) {
                filteredFlights.add(flight);
            }
        }

        return filteredFlights;
    }

    @Override
    public List<Flight> getFlightsWaitingTimeMoreThanHours(List<Flight> flights, Integer hour) {
        List<Flight> filteredFlights = new ArrayList<>();

        for (Flight flight : flights) {
            if (binaryFlightSearch.hasSegmentsWaitingHourMoreThan(flight, hour) || flight.getSegments().size() == 1) {
                filteredFlights.add(flight);
            }
        }

        return filteredFlights;
    }

    @Override
    public List<Flight> getWrongFlights(List<Flight> flights) {
        List<Flight> filteredFlights = new ArrayList<>();

        for (Flight flight : flights) {
            if (!binaryFlightSearch.hasSegmentDateArrivalBeforeDeparture(flight)) {
                filteredFlights.add(flight);
            }
        }

        return filteredFlights;
    }
}
