package de.saufshop;

public class ExpressVersand implements VersandKostenCalculator {
    private static final double VERSANDKOSTEN = 9.99;
    
    @Override
    public double berechneVersandkosten(double warenwert) {
        return VERSANDKOSTEN;
    }
    
    @Override
    public String getVersandart() {
        return "Express";
    }

    @Override
    public String toString() {
        return String.format("%s (%.2f€, mit Temperaturregelung)", getVersandart(), VERSANDKOSTEN);
    }
}