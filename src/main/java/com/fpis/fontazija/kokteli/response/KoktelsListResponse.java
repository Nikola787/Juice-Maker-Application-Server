package com.fpis.fontazija.kokteli.response;

import java.util.ArrayList;
import java.util.List;

public class KoktelsListResponse {

    private int idKoktela;
    private String nazivKoktela;
    private String nazivKategorije;
    private String slikaCase;
    private String nazivCase;
    private List<List<String>> sastojci;

    public KoktelsListResponse(){
        this.sastojci = new ArrayList<>();
    }

    public KoktelsListResponse(int idKoktela, String nazivKoktela, String nazivKategorije, String slikaCase, String nazivCase) {
        this.idKoktela = idKoktela;
        this.nazivKoktela = nazivKoktela;
        this.nazivKategorije = nazivKategorije;
        this.slikaCase = slikaCase;
        this.nazivCase = nazivCase;
        this.sastojci = new ArrayList<>();
    }

    public KoktelsListResponse(int idKoktela, String nazivKoktela, String nazivKategorije, String slikaCase, String nazivCase, List<List<String>> sastojci) {
        this.idKoktela = idKoktela;
        this.nazivKoktela = nazivKoktela;
        this.nazivKategorije = nazivKategorije;
        this.slikaCase = slikaCase;
        this.nazivCase = nazivCase;
        this.sastojci = sastojci;
    }

    public void addSastojak(String naziv, String kolicina, String mernaJedinica){
        this.sastojci.add(List.of(naziv, kolicina, mernaJedinica));
    }

    public String getNazivKoktela() {
        return nazivKoktela;
    }

    public String getNazivKategorije() {
        return nazivKategorije;
    }

    public String getSlikaCase() {
        return slikaCase;
    }

    public String getNazivCase() {
        return nazivCase;
    }

    public List<List<String>> getSastojci() {
        return sastojci;
    }

    public int getIdKoktela() {
        return idKoktela;
    }

    public void setIdKoktela(int idKoktela) {
        this.idKoktela = idKoktela;
    }

    public void setNazivKoktela(String nazivKoktela) {
        this.nazivKoktela = nazivKoktela;
    }

    public void setNazivKategorije(String nazivKategorije) {
        this.nazivKategorije = nazivKategorije;
    }

    public void setSlikaCase(String slikaCase) {
        this.slikaCase = slikaCase;
    }

    public void setNazivCase(String nazivCase) {
        this.nazivCase = nazivCase;
    }

    public void setSastojci(List<List<String>> sastojci) {
        this.sastojci = sastojci;
    }
}
