import com.gridnine.testing.model.Flight;
import com.gridnine.testing.service.FlightService;
import com.gridnine.testing.service.impl.FlightServiceImpl;
import org.junit.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FlightServiceImplTest {
    private final FlightService flightService = new FlightServiceImpl();
    private final CreatingFlightsForServiceTest creatingFlightsForServiceTest = new CreatingFlightsForServiceTest();

    @Test
    public void testGetFlightsDepartureTimeAfterNow() {
        List<Flight> flights = creatingFlightsForServiceTest.createListForDepartureTimeAfterNowTest(2,1);

        List<Flight> result = flightService.getFlightsDepartureTimeAfterNow(flights);

        assertEquals(2, result.size());
    }

    @Test
    public void testGetFlightsWaitingTimeMoreThanHours() {
        List<Flight> flights = creatingFlightsForServiceTest.createListForWaitingTimeMoreThanHours(2,3,3);

        List<Flight> result = flightService.getFlightsWaitingTimeMoreThanHours(flights, 2);

        assertEquals(2, result.size());
    }

    @Test
    public void testGetWrongFlights() {
        List<Flight> flights = creatingFlightsForServiceTest.createListForWrongFlights(1,2);

        List<Flight> result = flightService.getWrongFlights(flights);

        assertEquals(1, result.size());
    }
}
