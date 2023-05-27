import com.gridnine.testing.util.FlightBuilder;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class FlightBuilderTest {
    @Test
    public void testCreateFlight_ThrowsExceptionForOddNumberOfDates() throws NoSuchMethodException, IllegalAccessException {
        LocalDateTime date1 = LocalDateTime.now();
        LocalDateTime date2 = LocalDateTime.now().plusHours(1);
        LocalDateTime date3 = LocalDateTime.now().plusHours(2);
        String errorMsg = "you must pass an even number of dates";

        Method createFlightMethod = FlightBuilder.class.getDeclaredMethod("createFlight", LocalDateTime[].class);
        createFlightMethod.setAccessible(true);

        try {
            createFlightMethod.invoke(null, new Object[]{new LocalDateTime[]{date1, date2, date3}});

            fail("Expected IllegalArgumentException to be thrown.");
        } catch (InvocationTargetException e) {
            Throwable actualException = e.getTargetException();

            assertEquals(IllegalArgumentException.class, actualException.getClass());
            assertEquals(errorMsg, actualException.getMessage());
        }
    }
}
