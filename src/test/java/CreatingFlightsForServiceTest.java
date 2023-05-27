import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CreatingFlightsForServiceTest {

    public List<Flight> createListForDepartureTimeAfterNowTest(Integer countValid, Integer countWrong) {
        List<Flight> flightList = new ArrayList<>();
        for (int i = 0; i < countValid; i++) {
            flightList.add(createFlightWithDepartureAfterNow());
        }
        for (int i = 0; i < countWrong; i++) {
            flightList.add(createFlightWithDepartureBeforeNow());
        }
        return flightList;
    }

    public List<Flight> createListForWaitingTimeMoreThanHours(Integer countValid, Integer countWrong, Integer waitingHours) {
        List<Flight> flightList = new ArrayList<>();
        for (int i = 0; i < countValid; i++) {
            flightList.add(createFlightWithWaitingTimeLessThanTwoHours());
        }
        for (int i = 0; i < countWrong; i++) {
            flightList.add(createFlightWithWaitingTimeGreaterThanHours(waitingHours));
        }
        return flightList;
    }

    public List<Flight> createListForWrongFlights(Integer countValid, Integer countWrong) {
        List<Flight> flightList = new ArrayList<>();
        for (int i = 0; i < countValid; i++) {
            flightList.add(createFlightWithValidSegmentDateTime());
        }
        for (int i = 0; i < countWrong; i++) {
            flightList.add(createFlightWithWrongSegmentDateTime());
        }
        return flightList;
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
