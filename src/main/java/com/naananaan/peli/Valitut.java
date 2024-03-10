package com.naananaan.peli;

public class Valitut implements ValitutTuotteet {
    private String nimi;
    private double menot;

    public Valitut(String nimi, double menot) {
        this.nimi = nimi;
        this.menot = menot;
    }

    @Override
    public double getMenot(Koira koira){
        return menot;
    }

    @Override
    public String getNimi() {
        return nimi;
    }
}
