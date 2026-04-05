package de.saufshop;

import de.saufshop.payment.PaymentStrategy;
import java.time.LocalDate;
import java.util.*;

public class Bestellung {
    private static int bestellnummerZaehler = 1000;
    
    private int bestellnummer;
    private LocalDate datum;
    private List<WarenkorbItem> items;
    private VersandKostenCalculator versand;
    private PaymentStrategy payment;  // NEU!
    private String status;
    private String transactionId;     // NEU!
    
    public Bestellung(Warenkorb warenkorb, VersandKostenCalculator versand) {
        if (warenkorb.isEmpty()) throw new IllegalArgumentException("Warenkorb darf nicht leer sein!");
        
        this.bestellnummer = ++bestellnummerZaehler;
        this.datum = LocalDate.now();
        this.items = new ArrayList<>(warenkorb.getItems());
        this.versand = versand;
        this.status = "AKTIV";
    }
    
    // NEU: Payment hinzufügen
    public void setPaymentMethod(PaymentStrategy payment) {
        this.payment = payment;
    }
    
    // NEU: Zahlung durchführen
    public boolean processPayment() {
        if (payment == null) {
            throw new IllegalStateException("Zahlungsmethode nicht gesetzt!");
        }
        
        boolean success = payment.pay(getGesamtsumme());
        if (success) {
            this.transactionId = payment.getTransactionId();
            this.status = "BEZAHLT";
        }
        return success;
    }
    
    // Getters
    public int getBestellnummer() { return bestellnummer; }
    public LocalDate getDatum() { return datum; }
    public List<WarenkorbItem> getItems() { return new ArrayList<>(items); }
    public String getVersandart() { return versand.getVersandart(); }
    public String getPaymentMethod() { return payment != null ? payment.getPaymentMethod() : "Nicht gesetzt"; }
    public String getStatus() { return status; }
    public String getTransactionId() { return transactionId; }
    
    public double getWarensumme() {
        double summe = 0.0;
        for (WarenkorbItem item : items) {
            summe += item.getGesamtpreis();
        }
        return summe;
    }
    
    public double getVersandkosten() {
        return versand.berechneVersandkosten(getWarensumme());
    }
    
    public double getGesamtsumme() {
        return getWarensumme() + getVersandkosten();
    }
    
    public int getAnzahlPositionen() { return items.size(); }
    
    public int getGesamtMenge() {
        int menge = 0;
        for (WarenkorbItem item : items) {
            menge += item.getMenge();
        }
        return menge;
    }
    
    public void setStatus(String status) { this.status = status; }
    public void bestaetigung() { this.status = "BESTAETIGT"; }
    public void versendet() { this.status = "VERSENDET"; }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n╔════════════════════════════════════╗\n");
        sb.append("║          BESTELLBESTAETIGUNG         ║\n");
        sb.append("╚════════════════════════════════════╝\n\n");
        
        sb.append(String.format("Bestellnummer: #%d\n", bestellnummer));
        sb.append(String.format("Datum: %s\n", datum));
        sb.append(String.format("Status: %s\n", status));
        sb.append(String.format("Versand: %s\n", versand.getVersandart()));
        sb.append(String.format("Zahlungsart: %s\n\n", getPaymentMethod()));
        
        sb.append("┌─ ARTIKEL ─────────────────────────┐\n");
        for (WarenkorbItem item : items) {
            sb.append(String.format("│ %s\n", item));
        }
        sb.append("└────────────────────────────────────┘\n\n");
        
        sb.append(String.format("Warensumme:        %8.2f€\n", getWarensumme()));
        sb.append(String.format("Versandkosten:     %8.2f€\n", getVersandkosten()));
        sb.append("──────────────────────────────────────\n");
        sb.append(String.format("GESAMTBETRAG:      %8.2f€\n", getGesamtsumme()));
        
        if (transactionId != null) {
            sb.append(String.format("\nTransaktions-ID: %s\n", transactionId));
        }
        
        return sb.toString();
    }
}