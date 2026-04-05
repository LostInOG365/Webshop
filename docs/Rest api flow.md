# 🔄 SAUFSHOP REST-API FLOW

## Die komplette Bestellung:

```
┌─────────────────────────────────────────────────────────┐
│ 1. FRONTEND: Wein auswählen + in Warenkorb legen        │
│    GET /api/saufshop/weine → Wein-Liste                │
└─────────────────────────────────────────────────────────┘
                          ↓
┌─────────────────────────────────────────────────────────┐
│ 2. FRONTEND: Warenkorb befüllen + Versand wählen       │
│    items: [{weinId: 1, menge: 1}, ...]                 │
│    versandart: "standard" oder "express"               │
└─────────────────────────────────────────────────────────┘
                          ↓
┌─────────────────────────────────────────────────────────┐
│ 3. FRONTEND: Zahlungsart auswählen                      │
│    paymentMethod: "paypal" oder "creditcard"           │
│    paypalEmail: "user@example.com" oder                │
│    cardNumber: "4111111111111111"                       │
└─────────────────────────────────────────────────────────┘
                          ↓
┌─────────────────────────────────────────────────────────┐
│ 4. BACKEND: POST /api/saufshop/bestellungen            │
│    - Warenkorb erstellen                               │
│    - Versandart wählen (StandardVersand/ExpressVersand)│
│    - PaymentStrategy instanziieren                      │
│    - Bestellung erstellen                              │
│    - processPayment() aufrufen                         │
│    - Bestellung speichern                              │
└─────────────────────────────────────────────────────────┘
                          ↓
┌─────────────────────────────────────────────────────────┐
│ 5. BACKEND RESPONSE: BestellungDTO mit                  │
│    - bestellnummer (z.B. 1001)                          │
│    - warensumme (166.94€)                              │
│    - versandkosten (5.99€)                             │
│    - gesamtsumme (172.93€)                             │
│    - paymentMethod ("PayPal (ela@example.com)")        │
│    - status ("BEZAHLT")                                │
│    - transactionId ("PP-a1b2c3d4")                     │
└─────────────────────────────────────────────────────────┘
                          ↓
┌─────────────────────────────────────────────────────────┐
│ 6. FRONTEND: Bestätigungs-Seite anzeigen               │
│    "Vielen Dank! Transaktion: PP-a1b2c3d4"            │
└─────────────────────────────────────────────────────────┘
```

---

## 🎯 WAS DAHINTER PASSIERT

**Backend beim POST /bestellungen:**

```java
// 1. Warenkorb füllen
Warenkorb warenkorb = new Warenkorb();
for (CartItem item : request.items) {
    Wein w = weine.get(item.weinId);  // Wein aus DB
    warenkorb.addProdukt(w, item.menge);  // In Warenkorb
}

// 2. Versandart instanziieren (Strategy Pattern!)
VersandKostenCalculator versand = 
    "express".equals(request.versandart) 
        ? new ExpressVersand()      // 9.99€
        : new StandardVersand();     // 5.99€

// 3. Bestellung erstellen
Bestellung bestellung = new Bestellung(warenkorb, versand);

// 4. Zahlungsart setzen (Strategy Pattern!)
if ("paypal".equals(request.paymentMethod)) {
    bestellung.setPaymentMethod(
        new PaypalStrategy(request.paypalEmail)
    );
}

// 5. ZAHLUNG VERARBEITEN
bestellung.processPayment();  // ← Das ist der Magic Moment!
// payment.pay(bestellung.getGesamtsumme()) wird aufgerufen
// Kreditkarte wird belastet / PayPal überweist

// 6. Speichern
bestellungen.put(bestellung.getBestellnummer(), bestellung);
```

---

## 💰 ZWEI DESIGN PATTERNS IN AKTION

### 1️⃣ Strategy Pattern für VERSAND
```
VersandKostenCalculator ← Interface
        ↓
    ├─ StandardVersand  (5.99€)
    └─ ExpressVersand   (9.99€)
```

### 2️⃣ Strategy Pattern für PAYMENT
```
PaymentStrategy ← Interface
        ↓
    ├─ PaypalStrategy      (email)
    └─ CreditCardStrategy  (Karte)
```

**Beide sind austauschbar!** Du kannst einfach neue Strategien hinzufügen:
- Neue Versandart? → Einfach `new KlassischePostVersand implements VersandKostenCalculator`
- Neue Zahlungsart? → Einfach `new ApplePayStrategy implements PaymentStrategy`

---

## 🚀 NEXT STEPS

**Nächste Woche:** HTML/CSS Frontend, das diese API aufruft!