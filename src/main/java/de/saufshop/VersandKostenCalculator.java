package de.saufshop;

public interface VersandKostenCalculator {
    double berechneVersandkosten(double warenwert);
    String getVersandart();
}