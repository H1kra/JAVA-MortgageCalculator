package com.example.mortgageCalc.servis;

import com.example.mortgageCalc.enums.Intervals;
import org.springframework.stereotype.Service;

@Service
public class calculationService {

    public double regularPayment(double loanAmount, double interestRate, double repaymentPeriod, Intervals paymentInterval) {

        double i = (interestRate/100)/paymentInterval.getFrequency();
        double n = repaymentPeriod*paymentInterval.getFrequency();

        return loanAmount * (i/(1 - Math.pow(i+1, -n)));
    };

    public double totalPaid(double loanAmount, double interestRate, double repaymentPeriod, Intervals paymentInterval) {
        double i = (interestRate/100)/paymentInterval.getFrequency();
        double n = repaymentPeriod*paymentInterval.getFrequency();
        double regularPayment = loanAmount * (i/(1 - Math.pow(i+1, -n)));


        return regularPayment * n;
    };
    public double paidInInterest(double loanAmount, double interestRate, double repaymentPeriod, Intervals paymentInterval) {
        double i = (interestRate/100)/paymentInterval.getFrequency();
        double n = repaymentPeriod*paymentInterval.getFrequency();
        double regularPayment = loanAmount * (i/(1 - Math.pow(i+1, -n)));

        double totalPaid = regularPayment * n;

        return totalPaid - loanAmount;
    };
}
