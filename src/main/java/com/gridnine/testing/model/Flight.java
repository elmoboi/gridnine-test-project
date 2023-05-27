package com.gridnine.testing.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Bean that represents a flight.
 */
public class Flight {
    private final List<Segment> segments;

    public Flight(final List<Segment> segs) {
        segments = segs;
    }

    public List<Segment> getSegments() {
        return segments;
    }

    public LocalDateTime getEarliestDepartureDate() {
        if(segments.isEmpty()) {
            return null;
        }

        LocalDateTime earliestDepartureDate = segments.get(0).getDepartureDate();
        for(Segment segment : segments) {
            if(segment.getDepartureDate().isBefore(earliestDepartureDate)) {
                earliestDepartureDate = segment.getDepartureDate();
            }
        }
        return earliestDepartureDate;
    }

    @Override
    public String toString() {
        return segments.stream().map(Object::toString)
                .collect(Collectors.joining(" "));
    }
}
