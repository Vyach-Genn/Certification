package com.gridnine.testing.filters;

import com.gridnine.testing.util.FlightBuilder;
import com.gridnine.testing.model.Flight;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Тесты для фильтра, исключающего сегменты, где время прилёта раньше времени вылета.
 */
class ArrivalBeforeDepartureFilterTest {
    /**
     * Проверяет, что после фильтрации все сегменты всех рейсов имеют время прилёта не раньше времени вылета.
     */
    @Test
    void testFilter() {
        List<Flight> flights = FlightBuilder.createFlights();

        ArrivalBeforeDepartureFilter filter = new ArrivalBeforeDepartureFilter();
        List<Flight> result = filter.filter(flights);

        assertTrue(result.stream().allMatch(f -> f.getSegments().stream().allMatch(s -> !s.getArrivalDate().isBefore(s.getDepartureDate()))));
    }
} 