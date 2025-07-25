package com.gridnine.testing;

import com.gridnine.testing.filters.DepartureBeforeNowFilter;
import com.gridnine.testing.filters.ArrivalBeforeDepartureFilter;
import com.gridnine.testing.filters.ExcessiveGroundTimeFilter;
import com.gridnine.testing.model.Flight;
import com.gridnine.testing.util.FlightBuilder;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Flight> flights = FlightBuilder.createFlights();

        System.out.println("\nAll flights:");
        flights.forEach(System.out::println);

        System.out.println("\n1. Исключены перелёты с вылетом до текущего момента времени:");
        List<Flight> filtered1 = new DepartureBeforeNowFilter().filter(flights);
        filtered1.forEach(System.out::println);

        System.out.println("\n2. Исключены перелёты с сегментами, где дата прилёта раньше даты вылета:");
        List<Flight> filtered2 = new ArrivalBeforeDepartureFilter().filter(flights);
        filtered2.forEach(System.out::println);

        System.out.println("\n3. Исключены перелёты, где общее время на земле превышает 2 часа:");
        List<Flight> filtered3 = new ExcessiveGroundTimeFilter().filter(flights);
        filtered3.forEach(System.out::println);
    }
} 