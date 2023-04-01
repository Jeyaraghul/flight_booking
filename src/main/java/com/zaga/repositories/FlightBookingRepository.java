package com.zaga.repositories;

import javax.enterprise.context.ApplicationScoped;

import com.zaga.models.FlightBooking;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

@ApplicationScoped
public class FlightBookingRepository implements PanacheRepositoryBase<FlightBooking , Long> {
    
}
