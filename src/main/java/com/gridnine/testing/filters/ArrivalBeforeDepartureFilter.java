package com.gridnine.testing.filters;

import com.gridnine.testing.model.Flight;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Фильтр перелётов, исключающий те, у которых хотя бы один сегмент
 * имеет дату прилёта раньше даты вылета.
 * <p>
 * Используется для фильтрации некорректных сегментов.
 *
 * @author AI
 * @version 1.0
 */
public class ArrivalBeforeDepartureFilter implements FlightFilter {
    /**
     * Фильтрует список перелётов, исключая те, у которых хотя бы один сегмент
     * имеет дату прилёта раньше даты вылета.
     *
     * @param flights список перелётов для фильтрации
     * @return новый список перелётов, удовлетворяющих условию
     */
    @Override
    public List<Flight> filter(List<Flight> flights) {
        return flights.stream()
                .filter(flight -> flight.getSegments().stream()
                        .allMatch(segment -> !segment.getArrivalDate().isBefore(segment.getDepartureDate())))
                .collect(Collectors.toList());
    }
} 