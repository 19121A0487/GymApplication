package com.api.Gym.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "prices")
@AllArgsConstructor
@NoArgsConstructor
public class Price {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double basePrice;
    private double couponDiscount;
    private double finalPrice;
    private int trainingPeriod;

    @ManyToOne
    @JoinColumn(name = "customer_id",nullable = false)
    private Customer customer;
    
    public Price(double basePrice, double couponDiscount, double finalPrice, Customer customer) {
        this.basePrice = basePrice;
        this.couponDiscount = couponDiscount;
        this.finalPrice = finalPrice;
        this.customer = customer;
    }

}
