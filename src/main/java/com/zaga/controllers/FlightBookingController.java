package com.zaga.controllers;



import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.zaga.models.FlightBooking;
import com.zaga.services.FlightBookingService;

@Path("/flightbooking")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FlightBookingController {

    @Inject
    FlightBookingService service;

   


   @POST 
   @Path("/bookround")
   public Response bookFlightRoundTrip(@Valid FlightBooking flightBooking){
    
      FlightBooking result = service.bookFlight(flightBooking);

      return Response.status(Response.Status.CREATED).entity(result).build();

   }
   
   @DELETE
   @Path("/cancel")
   public Response cancelFlight(Long bookingId){
    
   Long result = service.deleteBooking(bookingId);
         return Response.status(Response.Status.OK).entity(result).build();
   }
   @DELETE
   @Path("/cancel/{flightId}")
   public Response cancelReturnFlight(Long bookingId , @PathParam("flightId") String flightId){

    String result = service.cancelReturn(bookingId, flightId);
     
    return Response.status(Response.Status.OK).entity(result).build();

   }
}
