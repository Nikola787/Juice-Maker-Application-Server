package com.fpis.fontazija.kokteli.dto;

import java.util.Set;

public class KoktelFilterRequest {

    String search;
    Set<String> kategorije;
    Set<String> sastojci;

    public KoktelFilterRequest(String search, Set<String> kategorije, Set<String> sastojci) {
        this.search = search;
        this.kategorije = kategorije;
        this.sastojci = sastojci;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public Set<String> getKategorije() {
        return kategorije;
    }

    public void setKategorije(Set<String> kategorije) {
        this.kategorije = kategorije;
    }

    public Set<String> getSastojci() {
        return sastojci;
    }

    public void setSastojci(Set<String> sastojci) {
        this.sastojci = sastojci;
    }
}
