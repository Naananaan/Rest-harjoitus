package com.naananaan.peli;

import java.util.List;
//import java.util.ArrayList;
import java.util.Arrays;

import com.naananaan.peli.Harrastus.HarrastusType;
import com.naananaan.peli.Herkut.HerkutType;

public class KoiranAsiat {
    private Koira koira;
    private double budjetti;

    public KoiranAsiat(Koira koira, double budjetti) {
        this.koira = koira;
        this.budjetti = budjetti;
    }

    public Koira getKoira() {
        return this.koira;
    }
    public double getBudjetti() {
        return budjetti;
    }
    public List<HerkutType> getAvailableHerkutTypes() {
        return Arrays.asList(HerkutType.values());
    }
    public List<HarrastusType> getAvailableHarrastusTypes() {
        return Arrays.asList(HarrastusType.values());
    }
}
