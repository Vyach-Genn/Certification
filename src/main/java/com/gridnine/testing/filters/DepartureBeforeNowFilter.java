package com.gridnine.testing.filters;

import com.gridnine.testing.model.Flight;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Фильтр перелётов, исключающий те, у которых хотя бы один сегмент
 * вылетает до текущего момента времени.
 * <p>
 * Используется для фильтрации устаревших перелётов.
 *
 * @author AI
 * @version 1.0
 */
public class DepartureBeforeNowFilter implements FlightFilter {
    /**
     * Фильтрует список перелётов, исключая те, у которых хотя бы один сегмент
     * вылетает до текущего момента времени.
     *
     * @param flights список перелётов для фильтрации
     * @return новый список перелётов, удовлетворяющих условию
     */
    @Override
    public List<Flight> filter(List<Flight> flights) {
        LocalDateTime now = LocalDateTime.now();
        return flights.stream()
                .filter(flight -> flight.getSegments().stream()
                        .allMatch(segment -> !segment.getDepartureDate().isBefore(now)))
                .collect(Collectors.toList());
    }
} 