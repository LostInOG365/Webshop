package de.saufshop;

public class WarenkorbItem {
    private Wein wein;
    private int menge;

    public WarenkorbItem(Wein wein, int menge) {
        if (menge <= 0) throw new IllegalArgumentException("Menge muss mindestens 1 sein!");
        this.wein = wein;
        this.menge = menge;
    }

    public Wein getWein() { return wein; }
    public int getMenge() { return menge; }

    public double getGesamtpreis() {
        return wein.getPreis() * menge;
    }

    public void setMenge(int menge) {
        if (menge <= 0) throw new IllegalArgumentException("Menge muss mindestens 1 sein!");
        this.menge = menge;
    }

    @Override
    public String toString() {
        return String.format("%s x%d = %.2f€", wein.getName(), menge, getGesamtpreis());
    }
}