package com.fpis.fontazija.kokteli.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

@Entity
@Table(name="kategorija")
public class Kategorija {

    @Id
    @Column(name = "id")
    @NotNull(message = "Value id kategorije cannot be null")
    private int id;

    @Column(name = "naziv_kategorije")
    @NotNull(message = "Value naziv kategorije cannot be null")
    @NotEmpty(message = "Value naziv kategorije  cannot be empty")
    private String nazivKategorije;

    @OneToMany(mappedBy = "kategorija")
    @JsonIgnore
    private Set<Koktel> kokteli;

    public Kategorija() {}

    public Kategorija(int id, String nazivKategorije) {
        this.id = id;
        this.nazivKategorije = nazivKategorije;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNazivKategorije() {
        return nazivKategorije;
    }

    public void setNazivKategorije(String nazivKategorije) {
        this.nazivKategorije = nazivKategorije;
    }

    public Set<Koktel> getKokteli() {
        return kokteli;
    }

    public void setKokteli(Set<Koktel> kokteli) {
        this.kokteli = kokteli;
    }

    @Override
    public String toString() {
        return "Kategorija{" +
                "id=" + id +
                ", nazivKategorije='" + nazivKategorije + '\'' +
                '}';
    }
}
