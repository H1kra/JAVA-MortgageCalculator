package com.example.mortgageCalc.servis;

import com.example.mortgageCalc.model.Calculator;
import jdk.jfr.Frequency;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class CalculationService {

    public static double regularPayment(Calculator calculator) {

        double i = (calculator.getInterestRate()/100)/calculator.getPaymentInterval().getFrequency();
        double n = calculator.getRepaymentPeriod()*calculator.getPaymentInterval().getFrequency();
        double paymentRegular = calculator.getLoanAmount() * (i/(1 - Math.pow(i+1, -n)));
        return Math.round(paymentRegular * 100.0) / 100.0;
    };

    public static double totalPaid(Calculator calculator) {
        double i = (calculator.getInterestRate()/100)/calculator.getPaymentInterval().getFrequency();
        double n = calculator.getRepaymentPeriod()*calculator.getPaymentInterval().getFrequency();
        double regularPayment = calculator.getLoanAmount() * (i/(1 - Math.pow(i+1, -n)));
        double paidTotal = regularPayment * n ;

        return  Math.round(paidTotal * 100.0) / 100.0;

    };
    public static double paidInInterest(Calculator calculator) {
        double i = (calculator.getInterestRate()/100)/calculator.getPaymentInterval().getFrequency();
        double n = calculator.getRepaymentPeriod()*calculator.getPaymentInterval().getFrequency();
        double regularPayment = calculator.getLoanAmount() * (i/(1 - Math.pow(i+1, -n)));
        double totalPaid = regularPayment * n;

        double painInterest =  totalPaid - calculator.getLoanAmount();
        return  Math.round(painInterest * 100.0) / 100.0;

    };
    public List<Double> allCalculations(Calculator calculator) {
        double regularPayment = regularPayment(calculator);
        double totalPaid = totalPaid(calculator);
        double paidInInterest = paidInInterest(calculator);
        return Arrays.asList(regularPayment, totalPaid, paidInInterest);
    };
}
