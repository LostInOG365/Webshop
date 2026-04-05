# 🍷 SAUFSHOP - Luxury Wine Shop

## Project Structure

```
saufshop/
├── index.html           ← Landing/Home Page
├── shop.html           ← Shop mit Filtern & Warenkorb
├── about.html          ← Über uns
├── contact.html        ← Kontaktformular
├── styles.css          ← Zentrale CSS (Luxury Design)
├── script.js           ← Zentrale JavaScript Logik
└── README.md           ← Diese Datei
```

## Features

✅ **Luxury Design** – Burgundy + Gold + Cream Palette
✅ **Responsive** – Mobile, Tablet, Desktop
✅ **Separate HTML Pages** – Professionelle Projektstruktur
✅ **Externe CSS** – Zentral verwaltete Styles
✅ **Shop mit Filtern** – Nach Name, Kategorie, Preis
✅ **Warenkorb System** – Live Berechnung
✅ **Versandoptionen** – Standard (5,99€) und Express (9,99€)
✅ **Payment Auswahl** – PayPal und Kreditkarte
✅ **Suchfunktion** – Überall zugänglich
✅ **Kontaktformular** – Mit Anfrage-Typ und Validierung
✅ **Bestätigungsscreen** – Mit Transaktions-ID

## Setup

1. Alle Dateien in einen Ordner kopieren
2. `index.html` im Browser öffnen
3. Fertig! 🎉

## Pages

### index.html
- Landing Page mit Hero-Section
- Feature Cards (Premium, Versand, Qualität, Preis)
- Search-Integration
- Call-to-Action zum Shop

### shop.html
- Produktraster mit Filtern
- Name-, Kategorie- und Preis-Filter
- Warenkorb mit Live-Berechnung
- Versandart-Auswahl
- Payment-Methode-Auswahl
- Checkout mit Bestätigung

### about.html
- Firmengeschichte
- Warum SAUFSHOP?
- Team-Vorstellung
- Kontaktdaten

### contact.html
- Kontaktformular mit Validierung
- Anfrage-Typ: Frage, Beschwerde, Zusammenarbeit, Newsletter, Wein-Empfehlung
- Erfolgsbestätigung mit Modal
- Responsive Design

## Design System

### Colors
- **Burgundy (Primary):** #3B1F1F
- **Burgundy Light:** #5a3a3a
- **Gold (Accent):** #D4AF37
- **Cream (Background):** #F5F1E8
- **Dark Gray:** #2a2a2a
- **Light Gray:** #f0ede5

### Typography
- **Headlines:** Georgia, Serif (elegant)
- **Body:** Trebuchet MS, Sans-serif

### Spacing & Shadows
- Großzügige Whitespace
- Subtile Shadows (0 2px 8px)
- Border Radius: 3-4px (minimal)

## Kontaktformular - Überarbeitet

✅ **Struktur** (wie VeloShop):
- NAME
- EMAIL
- ART DER ANFRAGE (Dropdown)
- BETREFF
- ANLIEGEN (Textarea)

✅ **Features**:
- Formular-Validierung
- Erkennt ungültige E-Mails
- Success Modal mit Zusammenfassung
- Pflichtfelder gekennzeichnet (*)
- Responsive Design

## JavaScript Logik

### script.js
- Alle Funktionen zentral
- `renderProducts()` – Produktanzeige
- `filterProducts()` – Filter-Logic
- `addToCart()` / `removeFromCart()` – Warenkorb
- `updateCart()` – Live-Berechnung
- `checkout()` – Bestellabschluss
- `submitContact()` – Formular-Handler

## Browser Support

✅ Chrome, Firefox, Safari, Edge (modern versions)
✅ Mobile (iOS Safari, Chrome Mobile)

## Customization

### Farben ändern:
Öffne `styles.css`, ändere die `:root` Variablen:
```css
:root {
    --burgundy: #3B1F1F;    ← Hauptfarbe
    --gold: #D4AF37;        ← Akzentfarbe
    --cream: #F5F1E8;       ← Hintergrund
}
```

### Weine hinzufügen:
Öffne `script.js`, erweitere das `wines` Array:
```javascript
const wines = [
    { id: 6, name: 'Neuer Wein', category: 'Region', price: 29.99, emoji: '🍇' },
    // ...
];
```

## Deployment

Diese Website kann auf jeden Web-Server deployed werden:
- Einfach alle Dateien hochladen
- Keine Datenbank benötigt (alles im Browser)
- JavaScript muss aktiviert sein

Für echte Bestellungen:
- Backend API implementieren
- Payment Gateway integrieren (Stripe, PayPal API)
- Email-Versand konfigurieren

## Version
Version 1.0 - Complete Luxury Wine Shop

---

**Made with 💜 & 🍷**