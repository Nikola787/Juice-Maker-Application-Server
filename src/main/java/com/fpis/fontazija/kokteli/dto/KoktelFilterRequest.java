package com.fpis.fontazija.kokteli.dto;

import java.util.Set;

public class KoktelFilterRequest {

    String search;
    Set<String> kategorije;
    Set<String> sastojci;
    int offset;
    int pageSize;

    public KoktelFilterRequest(String search, Set<String> kategorije, Set<String> sastojci, int offset, int pageSize) {
        this.search = search;
        this.kategorije = kategorije;
        this.sastojci = sastojci;
        this.offset = offset;
        this.pageSize = pageSize;
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

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
