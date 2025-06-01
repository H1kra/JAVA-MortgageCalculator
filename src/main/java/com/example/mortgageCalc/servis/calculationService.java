package com.example.mortgageCalc.servis;

import com.example.mortgageCalc.enums.Intervals;
import com.example.mortgageCalc.model.Calculator;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class calculationService {

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
    public List<Double> allCalculations(Calculator calculator) {
        double regularPayment = regularPayment(calculator);
        double totalPaid = totalPaid(calculator);
        double paidInInterest = paidInInterest(calculator);
        return Arrays.asList(regularPayment, totalPaid, paidInInterest);
    };
}
