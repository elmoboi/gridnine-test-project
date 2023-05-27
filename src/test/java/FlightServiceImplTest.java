import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;
import com.gridnine.testing.service.FlightService;
import com.gridnine.testing.service.impl.FlightServiceImpl;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FlightServiceImplTest {
    private final FlightService flightService = new FlightServiceImpl();

    @Test
    public void testGetFlightsDepartureTimeAfterNow() {
        Flight flight1 = createFlightWithDepartureAfterNow(); //valid
        Flight flight2 = createFlightWithDepartureBeforeNow(); //wrong
        Flight flight3 = createFlightWithDepartureAfterNow(); //valid

        List<Flight> flights = new ArrayList<>();
        flights.add(flight1);
        flights.add(flight2);
        flights.add(flight3);

        List<Flight> result = flightService.getFlightsDepartureTimeAfterNow(flights);

        assertEquals(2, result.size());
        assertTrue(result.contains(flight1));
        assertTrue(result.contains(flight3));
        assertFalse(result.contains(flight2));
    }

    private Flight createFlightWithDepartureAfterNow() {
        Segment segment1 = new Segment(LocalDateTime.now().plusHours(1), LocalDateTime.now().plusHours(2));
        Segment segment2 = new Segment(LocalDateTime.now().plusHours(3), LocalDateTime.now().plusHours(4));

        List<Segment> segments = new ArrayList<>();
        segments.add(segment1);
        segments.add(segment2);

        return new Flight(segments);
    }

    private Flight createFlightWithDepartureBeforeNow() {
        Segment segment1 = new Segment(LocalDateTime.now().minusHours(2), LocalDateTime.now().minusHours(1));
        Segment segment2 = new Segment(LocalDateTime.now().minusHours(3), LocalDateTime.now().minusHours(2));

        List<Segment> segments = new ArrayList<>();
        segments.add(segment1);
        segments.add(segment2);

        return new Flight(segments);
    }

    @Test
    public void testGetFlightsWaitingTimeMoreThanHours() {
        Flight flight1 = createFlightWithWaitingTimeLessThanTwoHours(); //valid
        Flight flight2 = createFlightWithWaitingTimeGreaterThanHours(3); //wrong
        Flight flight3 = createFlightWithWaitingTimeGreaterThanHours(2); //wrong
        Flight flight4 = createFlightWithWaitingTimeGreaterThanHours(1); //valid
        Flight flight5 = createFlightWithWaitingTimeLessThanTwoHours(); //valid

        List<Flight> flights = new ArrayList<>();
        flights.add(flight1);
        flights.add(flight2);
        flights.add(flight3);
        flights.add(flight4);
        flights.add(flight5);

        List<Flight> result = flightService.getFlightsWaitingTimeMoreThanHours(flights, 2);

        assertEquals(3, result.size());
        assertFalse(result.contains(flight2));
        assertFalse(result.contains(flight3));
        assertTrue(result.contains(flight1));
        assertTrue(result.contains(flight4));
        assertTrue(result.contains(flight5));
    }

    private Flight createFlightWithWaitingTimeLessThanTwoHours() {
        Segment segment1 = new Segment(LocalDateTime.now(), LocalDateTime.now().plusMinutes(30));
        Segment segment2 = new Segment(LocalDateTime.now().plusHours(1).plusMinutes(30), LocalDateTime.now().plusHours(2).plusMinutes(30));

        List<Segment> segments = new ArrayList<>();
        segments.add(segment1);
        segments.add(segment2);

        return new Flight(segments);
    }

    private Flight createFlightWithWaitingTimeGreaterThanHours(int hours) {
        Segment segment1 = new Segment(LocalDateTime.now(), LocalDateTime.now().plusMinutes(30));
        Segment segment2 = new Segment(LocalDateTime.now().plusHours(hours).plusMinutes(30), LocalDateTime.now().plusHours(hours + 1).plusMinutes(30));

        List<Segment> segments = new ArrayList<>();
        segments.add(segment1);
        segments.add(segment2);

        return new Flight(segments);
    }

    @Test
    public void testGetWrongFlights() {
        Flight flight1 = createFlightWithWrongSegmentDateTime();
        Flight flight2 = createFlightWithValidSegmentDateTime();

        List<Flight> flights = new ArrayList<>();
        flights.add(flight1);
        flights.add(flight2);

        List<Flight> result = flightService.getWrongFlights(flights);
        System.out.println(flights);
        System.out.println(result);

        assertEquals(1, result.size());
        assertFalse(result.contains(flight1));
        assertTrue(result.contains(flight2));
    }

    private Flight createFlightWithWrongSegmentDateTime() {
        Segment segment1 = new Segment(LocalDateTime.now().plusHours(2), LocalDateTime.now().minusHours(3));
        Segment segment2 = new Segment(LocalDateTime.now().plusHours(3), LocalDateTime.now().plusHours(4));

        List<Segment> segments = new ArrayList<>();
        segments.add(segment1);
        segments.add(segment2);

        return new Flight(segments);
    }

    private Flight createFlightWithValidSegmentDateTime() {
        Segment segment1 = new Segment(LocalDateTime.now().plusHours(1), LocalDateTime.now().plusHours(2));
        Segment segment2 = new Segment(LocalDateTime.now().plusHours(3), LocalDateTime.now().plusHours(4));

        List<Segment> segments = new ArrayList<>();
        segments.add(segment1);
        segments.add(segment2);

        return new Flight(segments);
    }
}
