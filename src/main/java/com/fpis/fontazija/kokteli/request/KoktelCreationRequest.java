package com.fpis.fontazija.kokteli.request;

import com.fpis.fontazija.kokteli.entity.*;

import java.util.List;


public class KoktelCreationRequest {

    private Koktel koktel;
    private List<Sastojak> sastojci;

    private List<KoktelSastojak> koktelSastojaks;

    public KoktelCreationRequest(Koktel koktel, List<Sastojak> sastojci, List<KoktelSastojak> koktelSastojaks) {
        this.koktel = koktel;
        this.sastojci = sastojci;
        this.koktelSastojaks = koktelSastojaks;
    }

    public Koktel getKoktel() {
        return koktel;
    }

    public List<Sastojak> getSastojci() {
        return sastojci;
    }

    public List<KoktelSastojak> getKoktelSastojaks() {
        return koktelSastojaks;
    }

    @Override
    public String toString() {
        return "KoktelCreationRequest{" +
                "koktel=" + koktel +
                ", sastojci=" + sastojci +
                ", koktelSastojaks=" + koktelSastojaks +
                '}';
    }
}
