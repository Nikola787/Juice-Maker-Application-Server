package com.fpis.fontazija.kokteli.dto;

import java.util.ArrayList;
import java.util.List;

public class KoktelDetailsResponse {

    private int koktelId;
    private String naziv;
    private String opis;
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

    public KoktelDetailsResponse(int koktelId, String naziv, String opis, String nacinPripreme, List<List<String>> sastojci) {
        this.koktelId = koktelId;
        this.naziv = naziv;
        this.opis = opis;
        this.nacinPripreme = nacinPripreme;
        this.sastojci = sastojci;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
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
