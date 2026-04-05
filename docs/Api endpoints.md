# 🍷 SAUFSHOP REST-API DOKUMENTATION

## Start der API
```bash
mvn spring-boot:run
```

Die API läuft dann unter: **http://localhost:8080/api/saufshop**

---

## 📋 ENDPOINTS

### 1️⃣ GET alle Weine auflisten
```
GET /api/saufshop/weine
```

**Antwort:**
```json
[
  {
    "id": 1,
    "name": "Château Margaux 2018",
    "kategorie": "ROT",
    "preis": 89.99,
    "alkoholgehalt": 13.5
  },
  {
    "id": 2,
    "name": "Sauvignon Blanc 2022",
    "kategorie": "WEISS",
    "preis": 18.99,
    "alkoholgehalt": 12.0
  }
]
```

---

### 2️⃣ GET einzelnen Wein
```
GET /api/saufshop/weine/1
```

**Antwort:**
```json
{
  "id": 1,
  "name": "Château Margaux 2018",
  "kategorie": "ROT",
  "preis": 89.99,
  "alkoholgehalt": 13.5
}
```

---

### 3️⃣ POST Bestellung erstellen (mit PayPal)
```
POST /api/saufshop/bestellungen
Content-Type: application/json
```

**Request Body:**
```json
{
  "items": [
    {
      "weinId": 1,
      "menge": 1
    },
    {
      "weinId": 2,
      "menge": 2
    }
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

### 4️⃣ POST Bestellung mit Kreditkarte
```
POST /api/saufshop/bestellungen
Content-Type: application/json
```

**Request Body:**
```json
{
  "items": [
    {
      "weinId": 3,
      "menge": 2
    }
  ],
  "versandart": "express",
  "paymentMethod": "creditcard",
  "cardNumber": "4111111111111111",
  "cardHolder": "Ela Student"
}
```

**Response:**
```json
{
  "bestellnummer": 1002,
  "warensumme": 33.98,
  "versandkosten": 9.99,
  "gesamtsumme": 43.97,
  "paymentMethod": "Kreditkarte (****1111)",
  "status": "BEZAHLT",
  "transactionId": "CC-x9y8z7w6"
}
```

---

### 5️⃣ GET Bestellung Details
```
GET /api/saufshop/bestellungen/1001
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

## 🧪 CURL EXAMPLES

### Alle Weine auflisten
```bash
curl http://localhost:8080/api/saufshop/weine
```

### Bestellung erstellen (PayPal)
```bash
curl -X POST http://localhost:8080/api/saufshop/bestellungen \
  -H "Content-Type: application/json" \
  -d '{
    "items": [{"weinId": 1, "menge": 1}],
    "versandart": "standard",
    "paymentMethod": "paypal",
    "paypalEmail": "test@example.com"
  }'
```

### Bestellung abrufen
```bash
curl http://localhost:8080/api/saufshop/bestellungen/1001
```

---

## 🎨 FEATURES

✅ **Strategy Pattern für Versand** (Standard/Express)
✅ **Strategy Pattern für Payment** (PayPal/CreditCard)
✅ **REST-API mit Spring Boot**
✅ **JSON Request/Response**
✅ **CORS aktiviert** (für Frontend-Integration)
✅ **Vollständige Geschäftslogik**