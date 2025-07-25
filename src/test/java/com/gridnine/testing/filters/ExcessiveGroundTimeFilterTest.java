package com.gridnine.testing.filters;

import com.gridnine.testing.util.FlightBuilder;
import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Тесты для фильтра, исключающего рейсы с чрезмерным временем на земле между сегментами.
 */
class ExcessiveGroundTimeFilterTest {
    /**
     * Проверяет, что после фильтрации все рейсы имеют суммарное время на земле между сегментами не более 2 часов.
     */
    @Test
    void testFilter() {
        List<Flight> flights = FlightBuilder.createFlights();

        ExcessiveGroundTimeFilter filter = new ExcessiveGroundTimeFilter();
        List<Flight> result = filter.filter(flights);

        assertTrue(result.stream().allMatch(f -> totalGroundTimeMinutes(f) <= 120));
    }

    private long totalGroundTimeMinutes(Flight flight) {
        List<Segment> segments = flight.getSegments();
        long total = 0;
        for (int i = 1; i < segments.size(); i++) {
            Segment prev = segments.get(i - 1);
            Segment next = segments.get(i);
            total += Duration.between(prev.getArrivalDate(), next.getDepartureDate()).toMinutes();
        }
        return total;
    }
} 