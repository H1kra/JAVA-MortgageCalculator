package com.example.mortgageCalc.enums;

public enum Intervals {
    MONTHLY("měsíční", 12),
    YEARLY("roční", 1),
    HALF_YEARLY("půlroční", 2),
    QUARTERLY("čtvrtletní", 4),
    FOUR_MONTHLY("čtyřměsíční", 3),
    BIMONTHLY("dvouměsíční", 6),
    DAILY("denní", 365);

    private final String label;
    private final int frequency;

    Intervals(String label, int frequency) {
        this.label = label;
        this.frequency = frequency;
    }

    public String getLabel() {
        return label;
    }

    public int getFrequency() {
        return frequency;
    }
}

