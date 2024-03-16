package com.fpis.fontazija.kokteli.dto;

import com.fpis.fontazija.kokteli.entity.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public class KoktelCreationRequest {

    @NotNull(message = "Image is not uploaded")
    @NotEmpty(message = "Image is not uploaded")
    private String base64Image;

    @Valid
    @NotNull(message = "Object Koktel cannot be null")
    private Koktel koktel;

    @Valid
    @NotNull
    @Size(min = 1, message = "Cocktail must have atleast 1 ingredient")
    private List<Sastojak> sastojci;

    @Valid
    @NotNull
    @Size(min = 1, message = "Cocktail must have atleast 1 ingredient")
    private List<KoktelSastojak> koktelSastojaks;


    public KoktelCreationRequest(String base64Image, Koktel koktel, List<Sastojak> sastojci, List<KoktelSastojak> koktelSastojaks) {
        this.base64Image = base64Image;
        this.koktel = koktel;
        this.sastojci = sastojci;
        this.koktelSastojaks = koktelSastojaks;
    }

    public String getBase64Image() {
        return base64Image;
    }

    public void setBase64Image(String base64Image) {
        this.base64Image = base64Image;
    }

    public void setKoktel(Koktel koktel) {
        this.koktel = koktel;
    }

    public void setSastojci(List<Sastojak> sastojci) {
        this.sastojci = sastojci;
    }

    public void setKoktelSastojaks(List<KoktelSastojak> koktelSastojaks) {
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
