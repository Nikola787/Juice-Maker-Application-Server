package com.fpis.fontazija.kokteli.dto;

import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

public class KoktelsListResponse {

    private int idKoktela;
    private String nazivKoktela;
    private String nazivKategorije;
    private byte[] slikaCase;
    private String nazivCase;


    public KoktelsListResponse(int idKoktela, String nazivKoktela, String nazivKategorije, byte[] slikaCase, String nazivCase) {
        this.idKoktela = idKoktela;
        this.nazivKoktela = nazivKoktela;
        this.nazivKategorije = nazivKategorije;
        this.slikaCase = slikaCase;
        this.nazivCase = nazivCase;
    }

    public KoktelsListResponse() {

    }


    public String getNazivKoktela() {
        return nazivKoktela;
    }

    public String getNazivKategorije() {
        return nazivKategorije;
    }

    public byte[] getSlikaCase() {
        return slikaCase;
    }

    public String getNazivCase() {
        return nazivCase;
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

    public void setSlikaCase(byte[] slikaCase) {
        this.slikaCase = slikaCase;
    }

    public void setNazivCase(String nazivCase) {
        this.nazivCase = nazivCase;
    }

}
