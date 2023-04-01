package com.zaga.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
//import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

@Entity
@JsonIgnoreProperties({"id"})
public class FlightBooking extends PanacheEntityBase{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Passenger> passenger;

    @OneToOne( cascade = CascadeType.ALL)
    @JoinColumn(name = "payment_id", referencedColumnName ="id")
    private Payment payment;

    @OneToMany( cascade = CascadeType.ALL)
    private List<FlightDetails> flightDetails;

    @Enumerated(EnumType.STRING)
    private TravelType travelType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public FlightBooking(Long id, List<Passenger> passenger, Payment payment, List<FlightDetails> flightDetails) {
        this.id = id;
        this.passenger = passenger;
        this.payment = payment;
        this.flightDetails = flightDetails;
    }

    public FlightBooking() {
    }

    @Override
    public String toString() {
        return "FlightBooking [id=" + id + ", passenger=" + passenger + ", payment=" + payment + ", flightDetails="
                + flightDetails + ", travelType=" + travelType + "]";
    }

   
    
}
