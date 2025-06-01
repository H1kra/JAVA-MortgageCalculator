package com.example.mortgageCalc.model;

import com.example.mortgageCalc.enums.Intervals;

public class Calculator {
    private double loanAmount;
    private double interestRate;
    private int repaymentPeriod;
    private Intervals paymentInterval;

    public Calculator(double loanAmount,double interestRate, int repaymentPeriod, Intervals paymentInterval){
        this.loanAmount = loanAmount;
        this.interestRate = interestRate;
        this.repaymentPeriod = repaymentPeriod;
        this.paymentInterval = paymentInterval;
    }


    public double getLoanAmount(){
        return loanAmount;
    }
    public double getInterestRate(){
        return interestRate;
    }
    public int getRepaymentPeriod(){
        return repaymentPeriod;
    }
    public Intervals getPaymentInterval(){
        return paymentInterval;
    }

    public void setLoanAmount(double loanAmount){
        this.loanAmount = loanAmount;
    }
    public void setInterestRate(double interestRate){
        this.interestRate = interestRate;
    }
    public void setRepaymentPeriod(int repaymentPeriod){
        this.repaymentPeriod = repaymentPeriod;
    }
    public void setPaymentInterval(Intervals paymentInterval){
        this.paymentInterval = paymentInterval;
    }


}
