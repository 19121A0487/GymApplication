package com.api.Gym.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.Gym.DTO.PriceResponse;
import com.api.Gym.Entity.Price;
import com.api.Gym.Service.PriceService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/prices")
public class PriceController {

    @Autowired
    private PriceService priceService;

    
    @GetMapping("/calculate")
    public ResponseEntity<PriceResponse> calculatePrice(
        @RequestParam int trainingPeriod,
        @RequestParam(required = false, defaultValue = "") String couponCode,
        @RequestParam Long customerId
    ) {
        try {
            // Calculate and save to DB
            Price savedPrice = priceService.calculateAndSavePrice(trainingPeriod, couponCode, customerId);

            PriceResponse response = new PriceResponse(
                savedPrice.getBasePrice(),
                savedPrice.getCouponDiscount(),
                savedPrice.getFinalPrice()
            );
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }


}
