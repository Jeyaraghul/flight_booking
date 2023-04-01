package com.zaga.services;

import java.util.List;

import com.zaga.models.FlightBooking;
import com.zaga.models.FlightDetails;
import com.zaga.models.Passenger;
import com.zaga.models.Payment;

public interface FlightBookingService {
    public void bookFlight(FlightBooking flightBooking, Passenger passenger, Payment payment ,FlightDetails flightDetails);
    public void bookFlight(FlightBooking flightBooking,List<Passenger> passengers, Payment payment ,List<FlightDetails> flightDetails);
    public void deleteBooking(Long bookingId);
}
