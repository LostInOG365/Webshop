package de.saufshop;

public class Wein {
    private int id;
    private String name;
    private String kategorie;
    private double preis;
    private double alkoholgehalt;
    private String herkunft;
    private int jahrgang;

    public Wein(int id, String name, String kategorie, double preis, 
                double alkoholgehalt, String herkunft, int jahrgang) {
        this.id = id;
        this.name = name;
        this.kategorie = kategorie;
        this.preis = preis;
        this.alkoholgehalt = alkoholgehalt;
        this.herkunft = herkunft;
        this.jahrgang = jahrgang;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getKategorie() { return kategorie; }
    public double getPreis() { return preis; }
    public double getAlkoholgehalt() { return alkoholgehalt; }
    public String getHerkunft() { return herkunft; }
    public int getJahrgang() { return jahrgang; }

    public void setPreis(double preis) {
        if (preis <= 0) throw new IllegalArgumentException("Preis muss größer als 0 sein!");
        this.preis = preis;
    }

    @Override
    public String toString() {
        return String.format("%s (%d) | %.2f€ | %s | %d%%alc | %s",
                name, jahrgang, preis, kategorie, (int)alkoholgehalt, herkunft);
    }
}