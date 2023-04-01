package com.zaga.repositories;

import javax.enterprise.context.ApplicationScoped;

import com.zaga.models.Payment;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

@ApplicationScoped
public class PaymentRepository implements PanacheRepositoryBase<Payment , Long> {
    
}
