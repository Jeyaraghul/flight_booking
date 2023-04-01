package com.zaga.services;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import com.zaga.models.FlightBooking;
import com.zaga.models.FlightDetails;
import com.zaga.models.Passenger;
import com.zaga.models.Payment;
import com.zaga.repositories.FlightBookingRepository;
import com.zaga.repositories.FlightDetailsRepository;

import com.zaga.repositories.PassengerRepository;
import com.zaga.repositories.PaymentRepository;

@ApplicationScoped
public class FlightBookingSeviceImplementation implements FlightBookingService{

    @Inject
    FlightBookingRepository fBookingRepository;

    @Inject
    FlightDetailsRepository fDetailsRepository;

    @Inject
    PassengerRepository passengerRepository;

    @Inject
    PaymentRepository paymentREPository;

    

    
    @Override
    @Transactional
    public void bookFlight(FlightBooking flightBooking, Passenger passenger, Payment payment ,FlightDetails flightDetails) {

        // Save the customer and payment entities
        passengerRepository.persist(passenger);
        
        paymentREPository.persist(payment);
        
        fDetailsRepository.persist(flightDetails);

        List<Passenger> passengers = new ArrayList<Passenger>();
        passengers.add(passenger);

        List<FlightDetails> flDetails = new ArrayList<FlightDetails>();
        flDetails.add(flightDetails);

        // Associate the customer and payment entities with the flight booking entity
        flightBooking.setPassenger(passengers);
        flightBooking.setPayment(payment);
        flightBooking.setFlightDetails(flDetails);
        
        // Save the flight booking entity
        fBookingRepository.persist(flightBooking);
    }
    
    @Override
    @Transactional
    public void bookFlight(FlightBooking flightBooking,List<Passenger> passengers, Payment payment ,List<FlightDetails> flightDetails) {
        System.out.println("------------payment----------------"+payment);
        FlightBooking flBooking = new FlightBooking();
        // Save the customer and payment entities
        
       // payment.persist();

        //passengerRepository.persist(passengers);
        //fDetailsRepository.persist(flightDetails);

        flBooking.setPassenger(passengers);
        flBooking.setFlightDetails(flightDetails);
        System.out.println("--------pay-"+payment);
        flBooking.setPayment(payment);
        System.out.println("--------------"+flBooking);
        
        // Save the flight booking entity
        //flightBooking.persist();
        flBooking.persist();
    }

    @Override
    @Transactional
    public void deleteBooking(Long bookingId) {
        // Find the FlightBooking entity
        FlightBooking flightBooking = FlightBooking.findById(bookingId);
        System.out.println("------------------"+flightBooking);
        if (flightBooking != null) {
            // Delete the associated Payment entity
            Payment payment = flightBooking.getPayment();
            if (payment != null) {
                payment.delete();
            }
            
            // Delete the associated Customer entity
            List<Passenger> passengers = flightBooking.getPassenger();
            if (passengers != null) {
                passengers.stream().forEach(passenger -> {
                    passengerRepository.delete(passenger);
                });
            }

            List<FlightDetails> flDetails = flightBooking.getFlightDetails();
            if (flDetails != null) {
                flDetails.stream().forEach(flightdetail -> {
                    fDetailsRepository.delete(flightdetail);
                });
            }
            
            // Delete the FlightBooking entity
            flightBooking.delete();
        }
    }
}
