package com.naananaan.peli;

public class Harrastus implements ValitutTuotteet{

    public enum HarrastusType{
        OLEILU,
        AGILITY,
        TOKO,
        RALLYTOKO,
        KOIRATANSSI,
        JALJESTYS,
        METSASTYS,
        ETSINTA,
        PELASTUS,
        KAVERIKOIRA,
        CANICROSS,
        NENATYOSKENTELY,
        DODO
    }

    private HarrastusType type;

    public Harrastus(HarrastusType type) {
        this.type = type;
    }
    @Override
    public double getMenot(Koira koira) {
        switch (type) {
            case OLEILU:
                return koira.getMinun() * 0;
            case AGILITY:
                return koira.getMinun() * 320;
            case TOKO:
                return koira.getMinun() * 180;
            case RALLYTOKO:
                return koira.getMinun() * 115;
            case KOIRATANSSI:
                return koira.getMinun() * 105;
            case JALJESTYS:
                return koira.getMinun() * 35;
            case METSASTYS:
                return koira.getMinun() * 130;
            case ETSINTA:
                return koira.getMinun() * 65;
            case PELASTUS:
                return koira.getMinun() * 95;
            case KAVERIKOIRA:
                return koira.getMinun() * 0;
            case CANICROSS:
                return koira.getMinun() * 145;
            case NENATYOSKENTELY:
                return koira.getMinun() * 140;
            case DODO:
                return koira.getMinun() * 80;
            default:
                throw new IllegalStateException("Virheellinen valinta: " + type);
        }
    }

    @Override
    public String getNimi() {
        switch (type) {
            case OLEILU:
                return "Kotikoira, ei harrastuksia";
            case AGILITY:
                return "Koirien esteratakilpailu.";
            case TOKO:
                return "Tottelevaisuuskoulutus";
            case RALLYTOKO:
                return "Tokon, agilityn ja koiratanssin yhdistelmä";
            case KOIRATANSSI:
                return "Koira ja ohjaaja suorittaa musiikin tahtiin liikeyhdistelmiä";
            case JALJESTYS:
                return "Koira etsii maastoosta hajujäljen, jota seuraa";
            case METSASTYS:
                return "Koira auttaa ihmistä jäljittämään riistaa";
            case ETSINTA:
                return "Koira etsii hajun, jonka ohjaaja pyytää etsimään";
            case PELASTUS:
                return "Kadonneiden ihmisten etsintää";
            case KAVERIKOIRA:
                return "Vapaaehtoistyötä, koiran kanssa vierailut esim. erilaisissa asumisyksiköissä tai kouluissa.";
            case CANICROSS:
                return "Koirajuoksua, jossa koira vetää juoksijaa.";
            case NENATYOSKENTELY:
                return "Aktivoi koiran nenänkäyttöä sekä kehittää aivotyöskentelyä";
            case DODO:
                return "Koiran ja omistajan lihaskuntoharjoittelua";
            default:
                throw new IllegalStateException("Virheellinen valinta: " + type);
            }
}
    public void setHarrastusType(HarrastusType type) {
        this.type = type;
    }
}