package com.naananaan.peli;


public class Herkut implements ValitutTuotteet {
    private HerkutType type;
    private double minun;
    
    public enum HerkutType {
        EIHERKKUJA,
        PURULUURULLA,
        PURISTELUU,
        POROPOTKA,
        BUFFALODONITSI,
        HAMMASHARJALUU,
        TOASTNAUTA,
        PUNOTTUPURULUU,
        LUUANKKA,
        KANAKIERRETIKKU,
        PURUSARVI
    }


    public Herkut(double minun, HerkutType type) {
        this.type = type;
        this.minun = minun;
    }

    public void setHerkutType(HerkutType type) {
        this.type = type;
    }

    @Override
    public double getMenot(Koira koira) {
        switch (type) {
            case EIHERKKUJA:
                return minun * 0;
            case PURULUURULLA:
                return minun * 1;
            case PURISTELUU:
                return minun * 1;
            case POROPOTKA: 
                return minun * 11;
            case BUFFALODONITSI:
                return minun * 4;
            case HAMMASHARJALUU:
                return minun * 3;
            case TOASTNAUTA:
                return minun * 6;
            case PUNOTTUPURULUU:
                return minun * 7;
            case LUUANKKA:
                return minun * 4;
            case KANAKIERRETIKKU:
                return minun * 1;
            case PURUSARVI:
                return minun * 2;
            default:
                throw new IllegalStateException("Virheellinen valinta: " + type);
        }
    }
    @Override
    public String getNimi() {
        switch (type) {
            case EIHERKKUJA:
                return "Ei herkkuja";
            case PURULUURULLA:
                return "Puruluurulla 6cm, naudannahkaa.";
            case PURISTELUU:
                return "Puristeluu 10cm, naudannahkaa";
            case POROPOTKA: 
                return "Poropotka 600g";
            case BUFFALODONITSI:
                return "Rengasmainen muoto, vesipuhvelinnahkaa ja lihaa.";
            case HAMMASHARJALUU:
                return "Koko S. Kasvispohjainen, sopii koiran hampaiden puhdistukseen.";
            case TOASTNAUTA:
                return "Naudannahkaa, joka sisältää naunaudanlihapihvin. Koko 12 cm.";
            case PUNOTTUPURULUU:
                return "Naudannahkaa koko 35cm.";
            case LUUANKKA:
                return "Naudannahkapuruluu ankanliha-omantäytteellä. Koko 12cm.";
            case KANAKIERRETIKKU:
                return "Pehmeä kana-kalaherkku, 50g.";
            case PURUSARVI:
                return "Kasvipohjainen purusarvi. Koko M.";
            default:
                throw new IllegalStateException("Virheellinen valinta: " + type);
        }
    }
    public double getMinun() {
        return minun;
    }
}

