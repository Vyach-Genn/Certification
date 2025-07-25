package com.gridnine.testing.filters;

import com.gridnine.testing.filters.FlightFilter;
import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Фильтр перелётов, исключающий те, у которых суммарное время,
 * проведённое на земле между сегментами, превышает два часа.
 * <p>
 * Используется для фильтрации неудобных стыковок.
 *
 * @author AI
 * @version 1.0
 */
public class ExcessiveGroundTimeFilter implements FlightFilter {

    /**
     * Фильтрует список перелётов, исключая те, у которых суммарное время
     * на земле между сегментами превышает два часа (120 минут).
     *
     * @param flights список перелётов для фильтрации
     * @return новый список перелётов, удовлетворяющих условию
     */
    @Override
    public List<Flight> filter(List<Flight> flights) {
        return flights.stream()
                .filter(flight -> totalGroundTimeMinutes(flight) <= FilterConstants.MAX_GROUND_TIME_MINUTES)
                .collect(Collectors.toList());
    }

    /**
     * Вычисляет суммарное время на земле между сегментами перелёта.
     *
     * @param flight перелёт
     * @return общее время на земле в минутах
     */
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