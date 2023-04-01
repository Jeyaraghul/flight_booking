package com.zaga.dto;

import java.util.List;

import com.zaga.models.FlightBooking;
import com.zaga.models.FlightDetails;
import com.zaga.models.Passenger;
import com.zaga.models.Payment;
import com.zaga.models.TravelType;

public class BookFlightRequestDto {
    
    private FlightBooking flightBooking;

    
    private List<Passenger> passenger;

   
    private Payment payment;

    
    private List<FlightDetails> flightDetails;

  
    private TravelType travelType;


    public FlightBooking getFlightBooking() {
        return flightBooking;
    }


    public void setFlightBooking(FlightBooking flightBooking) {
        this.flightBooking = flightBooking;
    }


    public List<Passenger> getPassenger() {
        return passenger;
    }


    public void setPassenger(List<Passenger> passenger) {
        this.passenger = passenger;
    }


    public Payment getPayment() {
        return payment;
    }


    public void setPayment(Payment payment) {
        this.payment = payment;
    }


    public List<FlightDetails> getFlightDetails() {
        return flightDetails;
    }


    public void setFlightDetails(List<FlightDetails> flightDetails) {
        this.flightDetails = flightDetails;
    }


    public TravelType getTravelType() {
        return travelType;
    }


    public void setTravelType(TravelType travelType) {
        this.travelType = travelType;
    }


    public BookFlightRequestDto(FlightBooking flightBooking, List<Passenger> passenger, Payment payment,
            List<FlightDetails> flightDetails, TravelType travelType) {
        this.flightBooking = flightBooking;
        this.passenger = passenger;
        this.payment = payment;
        this.flightDetails = flightDetails;
        this.travelType = travelType;
    }


    @Override
    public String toString() {
        return "BookFlightRequestDto [flightBooking=" + flightBooking + ", passenger=" + passenger + ", payment="
                + payment + ", flightDetails=" + flightDetails + ", travelType=" + travelType + "]";
    }


   
    
}
