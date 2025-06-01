package com.example.mortgageCalc.servis;

import com.example.mortgageCalc.model.Calculator;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class CalculationService {

    public static double regularPayment(Calculator calculator) {

        double i = (calculator.getInterestRate()/100)/calculator.getPaymentInterval().getFrequency();
        double n = calculator.getRepaymentPeriod()*calculator.getPaymentInterval().getFrequency();

        return calculator.getLoanAmount() * (i/(1 - Math.pow(i+1, -n)));
    };

    public static double totalPaid(Calculator calculator) {
        double i = (calculator.getInterestRate()/100)/calculator.getPaymentInterval().getFrequency();
        double n = calculator.getRepaymentPeriod()*calculator.getPaymentInterval().getFrequency();
        double regularPayment = calculator.getLoanAmount() * (i/(1 - Math.pow(i+1, -n)));


        return regularPayment * n;
    };
    public static double paidInInterest(Calculator calculator) {
        double i = (calculator.getInterestRate()/100)/calculator.getPaymentInterval().getFrequency();
        double n = calculator.getRepaymentPeriod()*calculator.getPaymentInterval().getFrequency();
        double regularPayment = calculator.getLoanAmount() * (i/(1 - Math.pow(i+1, -n)));

        double totalPaid = regularPayment * n;

        return totalPaid - calculator.getLoanAmount();
    };
    public Map<String, Double> allCalculations(Calculator calculator) {
        Map<String, Double> results = new LinkedHashMap<>();

        results.put("Regular Payment", regularPayment(calculator));
        results.put("Total Paid", totalPaid(calculator));
        results.put("Paid in Interest", paidInInterest(calculator));

        return results;
    }

}
