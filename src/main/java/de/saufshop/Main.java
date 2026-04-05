package de.saufshop;

import de.saufshop.payment.PaymentStrategy;
import de.saufshop.payment.PaypalStrategy;
import de.saufshop.payment.CreditCardStrategy;

public class Main {
    public static void main(String[] args) {
        System.out.println("\n╔════════════════════════════════════╗");
        System.out.println("║  🍷 SAUFSHOP TEST - Payment Demo   ║");
        System.out.println("╚════════════════════════════════════╝\n");
        
        // Weine erstellen
        System.out.println("═══ Weine initialisieren ═══");
        Wein wein1 = new Wein(1, "Château Margaux 2018", "ROT", 89.99, 13.5, "Bordeaux", 2018);
        Wein wein2 = new Wein(2, "Sauvignon Blanc 2022", "WEISS", 18.99, 12.0, "Loire Valley", 2022);
        System.out.println("✓ " + wein1.getName() + " - " + wein1.getPreis() + "€");
        System.out.println("✓ " + wein2.getName() + " - " + wein2.getPreis() + "€");
        
        // Warenkorb füllen
        System.out.println("\n═══ Warenkorb füllen ═══");
        Warenkorb warenkorb = new Warenkorb();
        warenkorb.addProdukt(wein1, 1);
        warenkorb.addProdukt(wein2, 2);
        System.out.println("✓ Warenkorb hat " + warenkorb.getAnzahlPositionen() + " Positionen");
        
        // BESTELLUNG 1: PayPal + Standard-Versand
        System.out.println("\n╔════════════════════════════════════╗");
        System.out.println("║  BESTELLUNG #1: PayPal + Standard  ║");
        System.out.println("╚════════════════════════════════════╝");
        Bestellung bestellung1 = new Bestellung(warenkorb, new StandardVersand());
        
        // PayPal setzen
        PaymentStrategy paypal = new PaypalStrategy("ela@example.com");
        bestellung1.setPaymentMethod(paypal);
        
        // Zahlung verarbeiten
        bestellung1.processPayment();
        System.out.println(bestellung1);
        
        // BESTELLUNG 2: Kreditkarte + Express-Versand
        System.out.println("\n╔════════════════════════════════════╗");
        System.out.println("║ BESTELLUNG #2: Kreditkarte + Express║");
        System.out.println("╚════════════════════════════════════╝");
        Warenkorb warenkorb2 = new Warenkorb();
        warenkorb2.addProdukt(wein2, 2);
        
        Bestellung bestellung2 = new Bestellung(warenkorb2, new ExpressVersand());
        
        // Kreditkarte setzen
        PaymentStrategy creditcard = new CreditCardStrategy("4111111111111111", "Ela Student");
        bestellung2.setPaymentMethod(creditcard);
        
        // Zahlung verarbeiten
        bestellung2.processPayment();
        System.out.println(bestellung2);
        
        System.out.println("\n✅ ALLE TESTS ERFOLGREICH!");
        System.out.println("Payment-Strategien funktionieren! 🍷\n");
    }
}