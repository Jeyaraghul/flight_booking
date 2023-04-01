package com.zaga.repositories;

import javax.enterprise.context.ApplicationScoped;

import com.zaga.models.Passenger;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

@ApplicationScoped
public class PassengerRepository implements PanacheRepositoryBase<Passenger , Long>{
    
}
