package com.example.mortgageCalc.controller;

import com.example.mortgageCalc.model.Calculator;
import com.example.mortgageCalc.servis.CalculationService;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/calculators")
@AllArgsConstructor
public class CalculatorController {

private CalculationService calculationService;
    @PostMapping("/calculate")
    public ResponseEntity<Map<String, Double>> calculation(@RequestBody Calculator calculator) {
        System.out.println(calculator);
        return ResponseEntity.ok(calculationService.allCalculations(calculator));
    }

//    @PostMapping("/paymentCalendar")
//    public ResponseEntity<?> paymentCalendar(@RequestBody Calculator calculator) {
//        System.out.println(calculator);
//        return ResponseEntity.ok(calculationservice.payment(calculator));
//    }

}
