package com.api.Gym.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.Gym.Service.PriceService;

@RestController
@RequestMapping("/api/prices")
public class PriceController {

    @Autowired
    private PriceService priceService;

    @GetMapping("/calculate")
    public ResponseEntity<Double> calculatePrice(
        @RequestParam int trainingPeriod,
        @RequestParam(required = false, defaultValue = "") String couponCode
    ) {
        try {
            double finalPrice = priceService.calculateFinalPrice(trainingPeriod, couponCode);
            return ResponseEntity.ok(finalPrice);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
