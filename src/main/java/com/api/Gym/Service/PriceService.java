package com.api.Gym.Service;

import org.springframework.stereotype.Service;

@Service
public class PriceService {

    public double getBasePrice(int trainingPeriod) {
        return switch (trainingPeriod) {
        case 1 -> 1000.0;
        case 3 -> 2700.0;
        case 6 -> 5000.0;
        case 12 -> 9000.0;
            default -> throw new IllegalArgumentException("Invalid period");
        };
    }

    public double applyDiscount(double basePrice, String couponCode) {
        double discount = 0.0;

        if ("FIT10".equalsIgnoreCase(couponCode)) {
            discount = basePrice * 0.10;
        } else if ("GYM20".equalsIgnoreCase(couponCode)) {
            discount = basePrice * 0.20;
        }

        return basePrice - discount;
    }

    public double calculateFinalPrice(int trainingPeriod, String couponCode) {
        double base = getBasePrice(trainingPeriod);
        return applyDiscount(base, couponCode);
    }
}
