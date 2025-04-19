package com.api.Gym.DTO;

import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

@Table(name="prices")
public class PriceResponse {

    private double basePrice;
    private double couponDiscount;
    private double finalPrice;

    // Constructor
    public PriceResponse(double basePrice, double couponDiscount, double finalPrice) {
        this.basePrice = basePrice;
        this.couponDiscount = couponDiscount;
        this.finalPrice = finalPrice;
    }

    // Getters
    public double getBasePrice() {
        return basePrice;
    }

    public double getCouponDiscount() {
        return couponDiscount;
    }

    public double getFinalPrice() {
        return finalPrice;
    }

    // Optional: Setters if you need them (can be added manually or use Lombok @Data)
}
