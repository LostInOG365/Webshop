package de.saufshop;

import java.util.*;

public class Warenkorb {
    private Map<Integer, WarenkorbItem> items;
    
    public Warenkorb() {
        this.items = new HashMap<>();
    }
    
    public void addProdukt(Wein wein, int menge) {
        if (menge <= 0) throw new IllegalArgumentException("Menge muss mindestens 1 sein!");
        
        if (items.containsKey(wein.getId())) {
            WarenkorbItem existierendesItem = items.get(wein.getId());
            existierendesItem.setMenge(existierendesItem.getMenge() + menge);
        } else {
            items.put(wein.getId(), new WarenkorbItem(wein, menge));
        }
    }
    
    public void entferneProdukt(int weinId) {
        items.remove(weinId);
    }
    
    public void reduziereProdukt(int weinId, int menge) {
        if (items.containsKey(weinId)) {
            WarenkorbItem item = items.get(weinId);
            int neueMenge = item.getMenge() - menge;
            
            if (neueMenge <= 0) {
                items.remove(weinId);
            } else {
                item.setMenge(neueMenge);
            }
        }
    }
    
    public double getGesamtpreis() {
        double summe = 0.0;
        for (WarenkorbItem item : items.values()) {
            summe += item.getGesamtpreis();
        }
        return summe;
    }
    
    public List<WarenkorbItem> getItems() {
        return new ArrayList<>(items.values());
    }
    
    public boolean isEmpty() {
        return items.isEmpty();
    }
    
    public void clear() {
        items.clear();
    }
    
    public int getAnzahlPositionen() {
        return items.size();
    }
    
    public int getGesamtMenge() {
        int menge = 0;
        for (WarenkorbItem item : items.values()) {
            menge += item.getMenge();
        }
        return menge;
    }
    
    @Override
    public String toString() {
        if (isEmpty()) return "Warenkorb ist leer";
        
        StringBuilder sb = new StringBuilder();
        sb.append("=== WARENKORB ===\n");
        for (WarenkorbItem item : items.values()) {
            sb.append(item.toString()).append("\n");
        }
        sb.append(String.format("Gesamt: %.2f€", getGesamtpreis()));
        return sb.toString();
    }
}