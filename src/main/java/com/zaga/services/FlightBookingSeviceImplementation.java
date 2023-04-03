package com.zaga.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

        // Save the passenger , flightdetails and  payment entities
        passengerRepository.persist(passenger);
        
        paymentREPository.persist(payment);
        
        fDetailsRepository.persist(flightDetails);

        List<Passenger> passengers = new ArrayList<Passenger>();
        passengers.add(passenger);

        List<FlightDetails> flDetails = new ArrayList<FlightDetails>();
        flDetails.add(flightDetails);

        // Associate the passenger and payment entities with the flight booking entity
        flightBooking.setPassenger(passengers);
        flightBooking.setPayment(payment);
        flightBooking.setFlightDetails(flDetails);
        
        // Save the flight booking entity
        fBookingRepository.persist(flightBooking);
    }
    
    @Override
    @Transactional
    public void bookFlight(FlightBooking flightBooking,List<Passenger> passengers, Payment payment ,List<FlightDetails> flightDetails) {
        
        FlightBooking flBooking = new FlightBooking();
        
        // Associate the passenger , flightdetails and payment entities with the flight booking entity
        flBooking.setPassenger(passengers);
        flBooking.setFlightDetails(flightDetails);
        flBooking.setPayment(payment);
        // Save the flight booking entity
        flBooking.persist();
    }
    @Override
    @Transactional
    public FlightBooking bookFlight(FlightBooking flightBooking) {
       
        flightBooking.persist();
        return flightBooking;
       
    }
    @Override
    @Transactional
    public Long deleteBooking(Long bookingId) {

        // Find the FlightBooking entity
        FlightBooking flightBooking = FlightBooking.findById(bookingId);
       
        if (flightBooking != null) {
            // Delete the associated Payment entities
            Payment payment = flightBooking.getPayment();
            if (payment != null) {
                payment.delete();
            }
            
            // Delete the associated Passenger entities
            List<Passenger> passengers = flightBooking.getPassenger();
            if (passengers != null) {
                passengers.stream().forEach(passenger -> {
                    passengerRepository.delete(passenger);
                });
            }
            // Delete the associated Flight Details entities
            List<FlightDetails> flDetails = flightBooking.getFlightDetails();
            if (flDetails != null) {
                flDetails.stream().forEach(flightdetail -> {
                    fDetailsRepository.delete(flightdetail);
                });
            }
            
            // Delete the FlightBooking entity
            flightBooking.delete();
        }
        return bookingId;
    }
    // Checking the expiry for flight cancelling
    // @Override
    // public Boolean canDelete(FlightBooking flightBooking){
    //     if (flightBooking.getFlightDetails().get(0).getDepartureDate().)
    // }


    //Cancelling only return or only one flight
    
    @Override
    @Transactional
    public String cancelReturn(Long bookingId , String flightId){
       // Find the FlightBooking entity
       FlightBooking flightBooking = FlightBooking.findById(bookingId);
       //Delete only particular flight and update the booking
       List<FlightDetails> flDetails = flightBooking.getFlightDetails();
       if (flDetails != null) {
          Optional<FlightDetails> flightdetail = flDetails.stream()
                                        .filter(detail -> detail.getFlightId().equals(flightId))
                                        .findFirst();

           if(flightdetail.isPresent()){
             System.out.println("Flight details:-------- " + flightdetail.get());
               FlightDetails.deleteById(flightdetail.get().getId());
           }                             
       }
      return flightId;

    }
}
