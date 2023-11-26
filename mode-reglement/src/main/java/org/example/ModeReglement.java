package org.example;

public class ModeReglement {
    private boolean estReception;

    public ModeReglement(boolean estReception, int precesionParMoins, int precesionParJourValeur, String precesionParJourType, int leJourMois) {
        this.estReception = estReception;
        this.precesionParMoins = precesionParMoins;
        this.precesionParJourValeur = precesionParJourValeur;
        this.precesionParJourType = precesionParJourType;
        this.leJourMois = leJourMois;
    }

    private int precesionParMoins;
    private int precesionParJourValeur;
    private String precesionParJourType;
    private int leJourMois;

    public boolean isEstReception() {
        return estReception;
    }

    public void setEstReception(boolean estReception) {
        this.estReception = estReception;
    }

    public int getPrecesionParMoins() {
        return precesionParMoins;
    }

    public void setPrecesionParMoins(int precesionParMoins) {
        this.precesionParMoins = precesionParMoins;
    }

    public int getPrecesionParJourValeur() {
        return precesionParJourValeur;
    }

    public void setPrecesionParJourValeur(int precesionParJourValeur) {
        this.precesionParJourValeur = precesionParJourValeur;
    }

    public String getPrecesionParJourType() {
        return precesionParJourType;
    }

    public void setPrecesionParJourType(String precesionParJourType) {
        this.precesionParJourType = precesionParJourType;
    }

    public int getLeJourMois() {
        return leJourMois;
    }

    public void setLeJourMois(int leJourMois) {
        this.leJourMois = leJourMois;
    }
}
