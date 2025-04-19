package com.api.Gym.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.Gym.Entity.Customer;
import com.api.Gym.Entity.Price;
import com.api.Gym.Repo.CustomerRepo;
import com.api.Gym.Repo.PriceRepository;

@Service
public class PriceService {

    @Autowired
    private PriceRepository priceRepository;

    @Autowired
    private CustomerRepo customerRepository;

    public double getBasePrice(int trainingPeriod) {
        return switch (trainingPeriod) {
            case 1 -> 1000.0;
            case 3 -> 2700.0;
            case 6 -> 5000.0;
            case 12 -> 9000.0;
            default -> throw new IllegalArgumentException("Invalid period");
        };
    }

    public double getCouponDiscount(double base, String couponCode) {
        if ("FIT10".equalsIgnoreCase(couponCode)) {
            return 0.1 * base;
        } else if ("GYM20".equalsIgnoreCase(couponCode)) {
            return 0.2 * base;
        }
        return 0;
    }

    public Price calculateAndSavePrice(int trainingPeriod, String couponCode, Long customerId) {
        double basePrice = getBasePrice(trainingPeriod);
        double discount = getCouponDiscount(basePrice, couponCode);
        double finalPrice = basePrice - discount;

        Customer customer = customerRepository.findById(customerId)
        		.orElseThrow(() -> {
        	        System.out.println("❌ Customer not found for ID: " + customerId);
        	        return new IllegalArgumentException("Customer not found with id: " + customerId);
        	    });
        
        
        // Check for existing price entry
        Price price = priceRepository.findByCustomerId(customerId).orElse(new Price());
        price.setCustomer(customer);
        price.setTrainingPeriod(trainingPeriod); // Add this field in your entity if not present
        price.setBasePrice(basePrice);
        price.setCouponDiscount(discount);
        price.setFinalPrice(finalPrice);

        return priceRepository.save(price);
    }

    
    
    
}
