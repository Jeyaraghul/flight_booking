package com.zaga.repositories;

import javax.enterprise.context.ApplicationScoped;

import com.zaga.models.FlightDetails;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

@ApplicationScoped
public class FlightDetailsRepository implements PanacheRepositoryBase<FlightDetails , Long> {
    
}
