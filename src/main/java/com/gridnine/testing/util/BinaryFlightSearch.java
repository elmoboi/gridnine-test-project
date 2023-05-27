package com.gridnine.testing.util;


import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class BinaryFlightSearch {

    public boolean hasFirstSegmentDepartureTimeAfterOrEqualToDateTime(Flight flight) {
        List<Segment> segments = flight.getSegments();

        int left = 0;
        int right = segments.size()-1;

        while (left <= right) {
            int mid = left+(right - left)/2;
            Segment segment = segments.get(mid);
            LocalDateTime departureDate = segment.getDepartureDate();

            if(departureDate.isAfter(LocalDateTime.now()) || departureDate.isEqual(LocalDateTime.now())) {
                return true;
            } else if(segment.getDepartureDate().isBefore(LocalDateTime.now())) {
                left = mid+1;
            } else {
                right = mid-1;
            }
        }
        return false;
    }

    public boolean hasSegmentDateArrivalBeforeDeparture(Flight flight) {
        List<Segment> segments = flight.getSegments();

        int left = 0;
        int right = segments.size()-1;

        while (left <= right) {
            int mid = left+(right - left)/2;
            Segment segment = segments.get(mid);
            LocalDateTime departureDate = segment.getDepartureDate();
            LocalDateTime arrivalDate = segment.getArrivalDate();

            if(departureDate.isAfter(arrivalDate)) {
                return true;
            } else if(segment.getDepartureDate().isBefore(LocalDateTime.now())) {
                left = mid+1;
            } else {
                right = mid-1;
            }
        }
        return false;
    }

    public boolean hasSegmentsWaitingHourMoreThan(Flight flight, Integer hour) {
        List<Segment> segments = flight.getSegments();

        int left = 0;
        int right = segments.size()-2;

        while (left <= right) {
            int mid = left+(right - left)/2;
            Segment firstSegment = segments.get(mid);
            Segment secondSegment = segments.get(mid+1);
            LocalDateTime departureDate = secondSegment.getDepartureDate();
            LocalDateTime arrivalDate = firstSegment.getArrivalDate();
            long hoursDifference = ChronoUnit.HOURS.between(arrivalDate,departureDate);

            if(hour > hoursDifference) {
                return true;
            } else {
                left = mid+1;
            }
        }
        return false;
    }
}