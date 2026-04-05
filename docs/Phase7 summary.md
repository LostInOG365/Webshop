# 🍷 SAUFSHOP PHASE 7 - REST-API & PAYMENT STRATEGY

## ✅ WAS HEUTE GEBAUT WURDE

### 1. PaymentStrategy Pattern (analog zu VersandKostenCalculator)
- **PaymentStrategy.java** – Interface mit `pay()` & `getTransactionId()`
- **PaypalStrategy.java** – PayPal-Implementierung
- **CreditCardStrategy.java** – Kreditkarten-Implementierung (maskiert Kartennummer)

### 2. Bestellung aktualisiert
- **Bestellung_Updated.java** – Mit PaymentStrategy
- Neue Methode: `setPaymentMethod(PaymentStrategy)`
- Neue Methode: `processPayment()` – führt Zahlung durch!
- Speichert `transactionId` nach erfolgreicher Zahlung

### 3. Spring Boot REST-API
- **SaufshopController.java** – REST-Endpoints
  - `GET /api/saufshop/weine` – Alle Weine auflisten
  - `GET /api/saufshop/weine/{id}` – Einzelnen Wein
  - `POST /api/saufshop/bestellungen` – Bestellung erstellen (mit Payment!)
  - `GET /api/saufshop/bestellungen/{id}` – Bestellung-Details

- **SaufshopApplication.java** – Spring Boot Main-Class
- **pom.xml** – Maven Dependencies (Spring Boot, MySQL)
- **application.properties** – Server-Config (Port 8080, Logging)

### 4. Dokumentation
- **API_ENDPOINTS.md** – Alle Endpoints mit Beispiele
- **REST_API_FLOW.md** – Kompletter Ablauf erklärt

---

## 🎯 WICHTIGSTE FEATURES

### ✨ Two Strategy Patterns in ACTION
```
Versand:          Payment:
├─ Standard       ├─ PayPal
└─ Express        └─ CreditCard
```

### 📍 POST Bestellung Flow
```json
{
  "items": [
    {"weinId": 1, "menge": 1},
    {"weinId": 2, "menge": 2}
  ],
  "versandart": "standard",
  "paymentMethod": "paypal",
  "paypalEmail": "ela@example.com"
}
```

**Response:**
```json
{
  "bestellnummer": 1001,
  "warensumme": 127.97,
  "versandkosten": 5.99,
  "gesamtsumme": 133.96,
  "paymentMethod": "PayPal (ela@example.com)",
  "status": "BEZAHLT",
  "transactionId": "PP-a1b2c3d4"
}
```

---

## 🚀 SO STARTEST DU DIE API

### Option 1: Mit Maven
```bash
cd saufshop
mvn clean install
mvn spring-boot:run
```

### Option 2: Mit IDE
- IntelliJ: `SaufshopApplication.java` → Run

### API läuft dann unter:
```
http://localhost:8080/api/saufshop
```

---

## 🧪 TEST MIT CURL

### Alle Weine auflisten:
```bash
curl http://localhost:8080/api/saufshop/weine
```

### Bestellung mit PayPal:
```bash
curl -X POST http://localhost:8080/api/saufshop/bestellungen \
  -H "Content-Type: application/json" \
  -d '{
    "items": [{"weinId": 1, "menge": 1}],
    "versandart": "standard",
    "paymentMethod": "paypal",
    "paypalEmail": "ela@test.com"
  }'
```

### Bestellung mit Kreditkarte:
```bash
curl -X POST http://localhost:8080/api/saufshop/bestellungen \
  -H "Content-Type: application/json" \
  -d '{
    "items": [{"weinId": 3, "menge": 2}],
    "versandart": "express",
    "paymentMethod": "creditcard",
    "cardNumber": "4111111111111111",
    "cardHolder": "Ela Student"
  }'
```

---

## 📊 ARCHITEKTUR

```
Frontend (nächste Woche!)
        ↓
REST-API (Spring Boot)
        ↓
SaufshopController
        ↓
┌─────────────────┬─────────────────┬─────────────────┐
│   Warenkorb     │   Versand       │   Payment       │
│   (Map-based)   │   (Strategy)    │   (Strategy)    │
├─────────────────┼─────────────────┼─────────────────┤
│ Standard        │ PayPal          │                 │
│ Express         │ CreditCard      │                 │
└─────────────────┴─────────────────┴─────────────────┘
        ↓
   Bestellung
   (mit Zahlung!)
        ↓
   Datenbank (MySQL)
```

---

## ✅ CHECKLIST

- [x] PaymentStrategy Pattern implementiert
- [x] PayPal & CreditCard Strategien
- [x] Bestellung integriert
- [x] Spring Boot REST-API
- [x] 5 Wein-Produkte (Test-Daten)
- [x] Swagger/API-Dokumentation
- [x] CORS aktiviert (für Frontend)

---

## 🎓 WAS DER KANZLER SIEHT

**Zwei Design Patterns:** ✅
- Strategy Pattern für Versand (VersandKostenCalculator)
- Strategy Pattern für Payment (PaymentStrategy)

**REST-API:** ✅
- Professionelle Spring Boot-Implementierung
- Clean Code, saubere Separation

**Skalierbarkeit:** ✅
- Neue Versandarten? → 10 Minuten
- Neue Zahlungsarten? → 10 Minuten
- Keine Code-Änderung in Bestellung.java!

---

## 🚀 NEXT STEP

**Nächste Woche:** Web-Frontend (HTML/CSS/JS) das diese API aufruft!

```html
<h2>Wein-Shop</h2>
<!-- GET /api/saufshop/weine -->
<div id="weine-liste"></div>

<h2>Warenkorb</h2>
<button onclick="bestellungErstellen()">
  <!-- POST /api/saufshop/bestellungen -->
  Jetzt kaufen!
</button>
```

---

## 📦 FILES IM OUTPUTS

- `PaymentStrategy.java` – Interface
- `PaypalStrategy.java` – PayPal-Implementierung
- `CreditCardStrategy.java` – Credit Card-Implementierung
- `Bestellung_Updated.java` – Mit Payment
- `SaufshopController.java` – REST-Endpoints
- `SaufshopApplication.java` – Spring Boot Main
- `pom.xml` – Maven-Config
- `application.properties` – Server-Config
- `API_ENDPOINTS.md` – Endpoint-Doku
- `REST_API_FLOW.md` – Flow-Erklärung
- `PHASE7_SUMMARY.md` – Diese Datei

---

**PHASE 7 STATUS: 50% DONE!** 🎉

**Nächstes Ziel: Web-Frontend!**