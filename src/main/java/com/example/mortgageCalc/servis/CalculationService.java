package com.example.mortgageCalc.servis;

import com.example.mortgageCalc.model.Calculator;
import jdk.jfr.Frequency;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class CalculationService {

    public static double regularPayment(Calculator calculator) {

        double i = (calculator.getInterestRate()/100)/calculator.getPaymentInterval().getFrequency();
        double n = calculator.getRepaymentPeriod()*calculator.getPaymentInterval().getFrequency();
        double paymentRegular = calculator.getLoanAmount() * (i/(1 - Math.pow(i+1, -n)));
        return Math.round(paymentRegular * 100.0) / 100.0;
    }

    public static double totalPaid(Calculator calculator) {
        double i = (calculator.getInterestRate()/100)/calculator.getPaymentInterval().getFrequency();
        double n = calculator.getRepaymentPeriod()*calculator.getPaymentInterval().getFrequency();
        double regularPayment = calculator.getLoanAmount() * (i/(1 - Math.pow(i+1, -n)));
        double paidTotal = regularPayment * n ;

        return  Math.round(paidTotal * 100.0) / 100.0;

    }

    public static double paidInInterest(Calculator calculator) {
        double i = (calculator.getInterestRate()/100)/calculator.getPaymentInterval().getFrequency();
        double n = calculator.getRepaymentPeriod()*calculator.getPaymentInterval().getFrequency();
        double regularPayment = calculator.getLoanAmount() * (i/(1 - Math.pow(i+1, -n)));
        double totalPaid = regularPayment * n;

        double painInterest =  totalPaid - calculator.getLoanAmount();
        return  Math.round(painInterest * 100.0) / 100.0;

    }

    public static double rpsn(Calculator calculator) {
        double i = (calculator.getInterestRate() / 100) / calculator.getPaymentInterval().getFrequency();
        double n = calculator.getRepaymentPeriod() * calculator.getPaymentInterval().getFrequency();
        double regularPayment = calculator.getLoanAmount() * (i / (1 - Math.pow(i + 1, -n)));
        double totalPaid = regularPayment * n;

        double cost = totalPaid - calculator.getLoanAmount();
        double years = calculator.getRepaymentPeriod();

        double rpsn = (cost / calculator.getLoanAmount()) / years * 100;
        return Math.round(rpsn * 100.0) / 100.0; // zaokrouhlení na 2 desetinná místa
    }

    public Map<String, Double> allCalculations(Calculator calculator) {
        Map<String, Double> results = new LinkedHashMap<>();

        results.put("Regular Payment", regularPayment(calculator));
        results.put("Total Paid", totalPaid(calculator));
        results.put("Paid in Interest", paidInInterest(calculator));
        results.put("RPSN", rpsn(calculator));

        return results;
    }

}
