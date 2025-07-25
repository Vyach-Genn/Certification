package com.gridnine.testing.filters;

import com.gridnine.testing.util.FlightBuilder;
import com.gridnine.testing.model.Flight;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Тесты для фильтра, исключающего сегменты с вылетом до текущего момента.
 */
class DepartureBeforeNowFilterTest {
    /**
     * Проверяет, что после фильтрации все сегменты всех рейсов имеют вылет не ранее текущего момента.
     */
    @Test
    void testFilter() {
        List<Flight> flights = FlightBuilder.createFlights();

        DepartureBeforeNowFilter filter = new DepartureBeforeNowFilter();
        List<Flight> result = filter.filter(flights);

        LocalDateTime now = LocalDateTime.now();
        assertTrue(result.stream().allMatch(f -> f.getSegments().stream().allMatch(s -> !s.getDepartureDate().isBefore(now))));
    }
} 