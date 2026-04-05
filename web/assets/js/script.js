// ========================
// SAUFSHOP - GLOBAL SCRIPT
// ========================

const wines = [
    { id: 1, name: 'Château Margaux 2018', category: 'Bordeaux', price: 89.99, 
      emoji: '🔴', image: '../assets/img/Rotwein.jpg' },
    { id: 2, name: 'Sauvignon Blanc 2022', category: 'Loire Valley', price: 18.99, 
      emoji: '⚪', image: '../assets/img/Weißwein.jpg' },
    { id: 3, name: 'Rosé de Provence 2023', category: 'Provence', price: 16.99, 
      emoji: '🩷', image: '../assets/img/Rose.jpg' },
    { id: 4, name: 'Prosecco Brut', category: 'Venetien', price: 12.99, 
      emoji: '✨', image: '../assets/img/Prosecco.jpg' },
    { id: 5, name: 'Pinot Noir Reserve 2020', category: 'Burgund', price: 34.99, 
      emoji: '🔴', image: '../assets/img/Pinot noir.jpg' }
];

let cart = {};
let shippingCost = 5.99;
let filteredWines = [...wines];

// ========================
// PAGE NAVIGATION
// ========================

function showPage(pageName) {
    // Hide all pages
    document.querySelectorAll('.page').forEach(p => p.classList.remove('active'));
    
    // Show selected page
    const targetPage = document.getElementById(pageName);
    if (targetPage) {
        targetPage.classList.add('active');
    }
    
    // Update nav active state
    document.querySelectorAll('nav a').forEach(link => link.classList.remove('active'));
    document.querySelector(`[data-page="${pageName}"]`)?.classList.add('active');
    
    // Initialize page-specific logic
    if (pageName === 'shop') {
        renderProducts();
    }
    
    window.scrollTo(0, 0);
}

// ========================
// PRODUCT & SHOP FUNCTIONS
// ========================

function renderProducts() {
    const grid = document.getElementById('productsGrid');
    if (!grid) return;
    
    if (filteredWines.length === 0) {
        grid.innerHTML = '<p style="grid-column: 1/-1; text-align: center; color: var(--text-secondary); padding: 3rem;">Keine Weine gefunden</p>';
        return;
    }
    
    grid.innerHTML = filteredWines.map(wine => `
        <div class="product-card">
            <img src="${wine.image}" alt="${wine.name}" style="width: 180px; height: 180px; object-fit: cover; border-radius: 4px;">
            <div class="product-name">${wine.name}</div>
            <div class="product-category">${wine.category}</div>
            <div class="product-price">${wine.price.toFixed(2)}€</div>
            <button class="add-btn" onclick="addToCart(${wine.id})">In Warenkorb</button>
        </div>
    `).join('');
}

function filterProducts() {
    const name = document.getElementById('nameFilter')?.value.toLowerCase() || '';
    const category = document.getElementById('categoryFilter')?.value || '';
    const price = parseFloat(document.getElementById('priceFilter')?.value) || Infinity;

    filteredWines = wines.filter(w => 
        w.name.toLowerCase().includes(name) &&
        (category === '' || w.category === category) &&
        w.price <= price
    );

    renderProducts();
}

function searchWines() {
    const query = document.getElementById('headerSearch')?.value || '';
    document.getElementById('nameFilter').value = query;
    showPage('shop');
    filterProducts();
}

function searchFromHero() {
    const query = document.getElementById('heroSearch')?.value || '';
    document.getElementById('nameFilter').value = query;
    showPage('shop');
    filterProducts();
}

// ========================
// CART FUNCTIONS
// ========================

function addToCart(wineId) {
    cart[wineId] = (cart[wineId] || 0) + 1;
    updateCart();
}

function removeFromCart(wineId) {
    if (cart[wineId] > 1) {
        cart[wineId]--;
    } else {
        delete cart[wineId];
    }
    updateCart();
}

function updateCart() {
    const cartItemsDiv = document.getElementById('cartItems');
    if (!cartItemsDiv) return;
    
    if (Object.keys(cart).length === 0) {
        cartItemsDiv.innerHTML = '<p style="text-align: center; color: var(--text-secondary); font-size: 0.9rem;">Leer</p>';
        const checkoutBtn = document.getElementById('checkoutBtn');
        if (checkoutBtn) checkoutBtn.disabled = true;
    } else {
        cartItemsDiv.innerHTML = Object.entries(cart).map(([wineId, qty]) => {
            const wine = wines.find(w => w.id === parseInt(wineId));
            const total = (wine.price * qty).toFixed(2);
            return `
                <div class="cart-item">
                    <div style="flex: 1; text-align: left;">
                        <strong>${wine.name}</strong><br/>
                        <span style="font-size: 0.85rem;">Menge: ${qty}</span>
                    </div>
                    <div style="font-weight: 700; text-align: right;">${total}€</div>
                </div>
            `;
        }).join('');
        
        const checkoutBtn = document.getElementById('checkoutBtn');
        if (checkoutBtn) checkoutBtn.disabled = false;
    }

    // Calculate totals
    const subtotal = Object.entries(cart).reduce((sum, [wineId, qty]) => {
        const wine = wines.find(w => w.id === parseInt(wineId));
        return sum + (wine.price * qty);
    }, 0);

    const shipping = document.querySelector('input[name="shipping"]:checked')?.value === 'express' ? 9.99 : 5.99;
    shippingCost = shipping;
    
    const total = subtotal + shippingCost;
    
    const subtotalEl = document.getElementById('subtotal');
    const shippingEl = document.getElementById('shipping');
    const totalEl = document.getElementById('total');
    const cartCountEl = document.getElementById('cartCount');
    
    if (subtotalEl) subtotalEl.textContent = subtotal.toFixed(2) + '€';
    if (shippingEl) shippingEl.textContent = shipping.toFixed(2) + '€';
    if (totalEl) totalEl.textContent = total.toFixed(2) + '€';
    if (cartCountEl) cartCountEl.textContent = Object.values(cart).reduce((a, b) => a + b, 0);
}

// Shipping change listener
document.addEventListener('DOMContentLoaded', () => {
    document.querySelectorAll('input[name="shipping"]').forEach(radio => {
        radio.addEventListener('change', updateCart);
    });
});

// ========================
// CHECKOUT
// ========================

function checkout() {
    if (Object.keys(cart).length === 0) {
        alert('Warenkorb ist leer!');
        return;
    }

    const subtotal = Object.entries(cart).reduce((sum, [wineId, qty]) => {
        const wine = wines.find(w => w.id === parseInt(wineId));
        return sum + (wine.price * qty);
    }, 0);

    const payment = document.querySelector('input[name="payment"]:checked')?.value || 'paypal';
    const total = subtotal + shippingCost;
    const transId = payment === 'paypal' ? 
        'PP-' + Math.random().toString(36).substring(7).toUpperCase() : 
        'CC-' + Math.random().toString(36).substring(7).toUpperCase();

    const details = document.getElementById('confirmationDetails');
    if (details) {
        details.innerHTML = `
            <div class="detail-row">
                <span>Bestellnummer:</span>
                <span>#${Math.floor(Math.random() * 10000) + 1000}</span>
            </div>
            <div class="detail-row">
                <span>Warensumme:</span>
                <span>${subtotal.toFixed(2)}€</span>
            </div>
            <div class="detail-row">
                <span>Versand:</span>
                <span>${shippingCost.toFixed(2)}€</span>
            </div>
            <div class="detail-row">
                <span>Gesamtbetrag:</span>
                <span style="font-weight: 700; color: var(--gold);">${total.toFixed(2)}€</span>
            </div>
            <div class="detail-row">
                <span>Zahlungsart:</span>
                <span>${payment === 'paypal' ? 'PayPal' : 'Kreditkarte'}</span>
            </div>
            <div class="detail-row">
                <span>Transaktions-ID:</span>
                <span>${transId}</span>
            </div>
        `;
    }

    const modal = document.getElementById('confirmationModal');
    if (modal) modal.classList.add('active');
    
    cart = {};
    updateCart();
}

function resetShop() {
    const modal = document.getElementById('confirmationModal');
    if (modal) modal.classList.remove('active');
    showPage('shop');
}

// ========================
// CONTACT FORM
// ========================

function submitContact() {
    const name = document.getElementById('contactName')?.value.trim() || '';
    const email = document.getElementById('contactEmail')?.value.trim() || '';
    const subject = document.getElementById('contactSubject')?.value.trim() || '';
    const message = document.getElementById('contactMessage')?.value.trim() || '';
    const type = document.getElementById('contactType')?.value || '';

    if (!name || !email || !subject || !message || !type) {
        alert('Bitte alle Felder ausfüllen!');
        return;
    }

    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!emailRegex.test(email)) {
        alert('Bitte eine gültige E-Mail eingeben!');
        return;
    }

    console.log('Nachricht eingegangen:', { name, email, subject, message, type });
    
    alert(`Danke für deine Nachricht, ${name}!\nWir melden uns bald unter ${email}.`);
    
    document.getElementById('contactName').value = '';
    document.getElementById('contactEmail').value = '';
    document.getElementById('contactSubject').value = '';
    document.getElementById('contactMessage').value = '';
    document.getElementById('contactType').value = '';
    
    showPage('home');
}

// ========================
// INITIAL SETUP
// ========================

document.addEventListener('DOMContentLoaded', () => {
    showPage('home');
    updateCart();
});