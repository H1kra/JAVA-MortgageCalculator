package com.example.mortgageCalc.servis;

import com.example.mortgageCalc.model.Calculator;
import jdk.jfr.Frequency;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

@Service
public class CalculationService {

    private static class LoanDetails {
        double i;
        double n;
        double regularPayment;

        LoanDetails(Calculator calculator) {
            this.i = (calculator.getInterestRate() / 100) / calculator.getPaymentInterval().getFrequency();
            this.n = calculator.getRepaymentPeriod() * calculator.getPaymentInterval().getFrequency();
            this.regularPayment = calculator.getLoanAmount() * (i / (1 - Math.pow(i + 1, -n)));
        }
    }

    public static double regularPayment(Calculator calculator) {

        LoanDetails details = new LoanDetails(calculator);
        return Math.round(details.regularPayment * 100.0) / 100.0;
    };

    public static double totalPaid(Calculator calculator) {
        LoanDetails details = new LoanDetails(calculator);
        double totalPaid = details.regularPayment * details.n;
        return  Math.round(totalPaid * 100.0) / 100.0;

    }

    public static double paidInInterest(Calculator calculator) {
        LoanDetails details = new LoanDetails(calculator);
        double totalPaid = details.regularPayment * details.n;
        double paidInterest = totalPaid - calculator.getLoanAmount();
        return  Math.round(paidInterest * 100.0) / 100.0;

    }

    public static double rpsn(Calculator calculator) {
        double nominalRate = calculator.getInterestRate() / 100.0; // e.g., 7% => 0.07
        int frequency = calculator.getPaymentInterval().getFrequency(); // e.g., 12 for monthly

        double rpsn = Math.pow(1 + (nominalRate / frequency), frequency) - 1;

        return Math.round(rpsn * 10000.0) / 100.0;
    }

    public Map<String, Double> allCalculations(Calculator calculator) {
        Map<String, Double> results = new LinkedHashMap<>();

        results.put("Regular Payment", regularPayment(calculator));
        results.put("Total Paid", totalPaid(calculator));
        results.put("Paid in Interest", paidInInterest(calculator));
        results.put("RPSN", rpsn(calculator));

        return results;
    }

    public String generateCSV(Calculator calculator, String filePath) throws IOException {
        LoanDetails details = new LoanDetails(calculator);

        double remaining = calculator.getLoanAmount();

        try (FileWriter fr = new FileWriter(filePath, true);
             PrintWriter writer = new PrintWriter(fr)) {

            writer.println("Order;Payment;Interest;Principal Repayment;Loan");

            for (int i = 1; i <= details.n; i++) {
                double interest = remaining * details.i;
                double principal = details.regularPayment - interest;
                remaining -= principal;

                if (remaining < 0) remaining = 0;

                writer.printf(Locale.GERMANY, "%d;%.2f;%.2f;%.2f;%.2f%n",
                        i,
                        details.regularPayment,
                        interest,
                        principal,
                        remaining);
            }
            return filePath;

    } catch (IOException e) {
        e.printStackTrace();
        return null;
    }




}}
