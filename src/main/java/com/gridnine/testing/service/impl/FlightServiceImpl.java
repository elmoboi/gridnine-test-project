package com.gridnine.testing.service.impl;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;
import com.gridnine.testing.service.FlightService;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class FlightServiceImpl implements FlightService {

    @Override
    public List<Flight> getFlightsDepartureTimeAfterNow(List<Flight> flights) {
        if (flights == null) {
            return Collections.emptyList();
        }
        return flights.stream()
                .filter(flight -> flight.getSegments()
                        .stream()
                        .anyMatch(segment -> segment.getDepartureDate().isAfter(LocalDateTime.now()) ||
                                segment.getDepartureDate().isEqual(LocalDateTime.now())))
                .collect(Collectors.toList());
    }

    @Override
    public List<Flight> getFlightsWaitingTimeLessThanHours(List<Flight> flights, Integer hour) {
        if (flights == null) {
            return Collections.emptyList();
        }
        return flights.stream()
                .filter(flight -> !hasSegmentsWaitingHourMoreThan(flight, hour)).collect(Collectors.toList());
    }

    @Override
    public List<Flight> getRightFlights(List<Flight> flights) {
        if (flights == null) {
            return Collections.emptyList();
        }
        return flights.stream()
                .filter(flight -> flight.getSegments().stream()
                        .allMatch(segment -> segment.getArrivalDate().isAfter(segment.getDepartureDate())))
                .collect(Collectors.toList());
    }

    private boolean hasSegmentsWaitingHourMoreThan(Flight flight, Integer hour) {
        List<Segment> segments = flight.getSegments();
        for (int i = 0; i < segments.size() - 1; i++) {
            Segment firstSegment = segments.get(i);
            Segment secondSegment = segments.get(i + 1);

            LocalDateTime departureDate = secondSegment.getDepartureDate();
            LocalDateTime arrivalDate = firstSegment.getArrivalDate();

            Duration differenceTime = Duration.between(departureDate, arrivalDate);
            long diff = Math.abs(differenceTime.toHours());

            if (diff >= hour) {
                return true;
            }
        }

        return false;
    }
}
