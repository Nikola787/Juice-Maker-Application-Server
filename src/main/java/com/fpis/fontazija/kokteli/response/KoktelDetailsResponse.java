package com.fpis.fontazija.kokteli.response;

import java.util.ArrayList;
import java.util.List;

public class KoktelDetailsResponse {

    private int koktelId;
    // - na osnovu Id koktela GET koktel(nacinPripreme), niz(sastojci), niz(koktelSastojak(kolicina,mernaJedinica))
    private String nacinPripreme;
    private List<List<String>> sastojci;

    public KoktelDetailsResponse(){
        sastojci = new ArrayList<>();
    }

    public KoktelDetailsResponse(int koktelId, String nacinPripreme) {
        this.koktelId = koktelId;
        this.nacinPripreme = nacinPripreme;
        this.sastojci = new ArrayList<>();
    }

    public int getKoktelId() {
        return koktelId;
    }

    public void setKoktelId(int koktelId) {
        this.koktelId = koktelId;
    }

    public String getNacinPripreme() {
        return nacinPripreme;
    }

    public void setNacinPripreme(String nacinPripreme) {
        this.nacinPripreme = nacinPripreme;
    }

    public List<List<String>> getSastojci() {
        return sastojci;
    }

    public void setSastojci(List<List<String>> sastojci) {
        this.sastojci = sastojci;
    }

    public void addSastojak(String naziv, String kolicina, String mernaJedinica){
        this.sastojci.add(List.of(naziv, kolicina, mernaJedinica));
    }
}
