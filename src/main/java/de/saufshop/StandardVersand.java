package de.saufshop;

public class StandardVersand implements VersandKostenCalculator {
    private static final double VERSANDKOSTEN = 5.99;
    
    @Override
    public double berechneVersandkosten(double warenwert) {
        return VERSANDKOSTEN;
    }
    
    @Override
    public String getVersandart() {
        return "Standard";
    }

    @Override
    public String toString() {
        return String.format("%s (%.2f€)", getVersandart(), VERSANDKOSTEN);
    }
}