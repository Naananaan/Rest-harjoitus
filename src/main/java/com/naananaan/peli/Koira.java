package com.naananaan.peli;

import java.util.ArrayList;
import java.util.List;

import com.naananaan.peli.Harrastus.HarrastusType;
import com.naananaan.peli.Herkut.HerkutType;

public class Koira {
    private String rotu;
    private String koiranNimi;
    private String ulkoilu;
    private double minun;
    private double budjetti;
    private Harrastus harrastus = new Harrastus(HarrastusType.OLEILU);
    private List <Herkut> herkuts = new ArrayList<>();

    public Koira(String rotu, String koiranNimi, String ulkoilu, double budjetti, double minun){
        this.rotu = rotu;
        this.koiranNimi = koiranNimi;
        this.ulkoilu = ulkoilu;
        this.minun = minun;
        this.budjetti = budjetti;
    
        //Herkut.add(new Herkut);
    }
    public Koira(String rotu2, String koiranNimi2, String ulkoilu2, double budjetti2) {
        //TODO Auto-generated constructor stub
    }
    public void updateHarrastus(HarrastusType type) {
        harrastus.setHarrastusType(type);
    }
    public void updateHerkut(List<Herkut> herkuts, int index, HerkutType newType) {
        if (index >= 0 && index < this.herkuts.size()) {
            Herkut herkut = this.herkuts.get(index);
            herkut.setHerkutType(newType);
        } else {
            throw new IllegalArgumentException("Virheellinen valinta:  " + index);
        }
    }
    public Harrastus getHarrastus() {
        return harrastus;
    }
    public List <Herkut>getHerkuts() {
        return herkuts;
    }
    public String getRotu() {
        return this.rotu;
    }
    public String getKoiranNimi() {
        return this.koiranNimi;
    }
    public String getUlkoilu() {
        return this.ulkoilu;
    }
    public double getMinun() {
        return this.minun;
    }

    public double getMenot() {
        double menot = harrastus.getMenot(this);
        for (Herkut herkut : herkuts) {
            menot += herkut.getMenot(this);
        }
        return menot;
    }

}
