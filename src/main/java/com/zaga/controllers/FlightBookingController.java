package com.zaga.controllers;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.zaga.dto.BookFlightRequestDto;
import com.zaga.models.FlightBooking;
import com.zaga.models.FlightDetails;
import com.zaga.models.Passenger;
import com.zaga.models.Payment;
import com.zaga.services.FlightBookingService;

@Path("/flightbooking")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FlightBookingController {

    @Inject
    FlightBookingService service;

   
//    @POST 
//    @Path("/book")
//    public void bookFlight(FlightBooking flightBooking, Passenger passenger, Payment payment ,FlightDetails flightDetails){
    
//       service.bookFlight(flightBooking, passenger, payment, flightDetails);

//    }

   @POST 
   @Path("/bookround")
   public void bookFlightRoundTrip(BookFlightRequestDto dto){
    
      service.bookFlight(dto.getFlightBooking(), dto.getPassenger(), dto.getPayment(), dto.getFlightDetails());

   }
   
   @DELETE
   @Path("/cancel")
   public void cancelFlight(Long bookingId){
    
    service.deleteBooking(bookingId);

   }

}
