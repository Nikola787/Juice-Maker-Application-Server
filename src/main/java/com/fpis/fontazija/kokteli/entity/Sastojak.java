package com.fpis.fontazija.kokteli.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Set;

@Entity
@Table(name = "sastojak")
public class Sastojak {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "naziv")
    @NotNull
    @NotEmpty
    @Size(min = 2, message = "Naziv sastojka must be at least 2 characters long")
    private String naziv;

    @OneToMany(mappedBy = "sastojak")
    private Set<KoktelSastojak> kokteli;

    public Sastojak() {}

    public Sastojak(String naziv) {
        this.naziv = naziv;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Set<KoktelSastojak> getKokteli() {
        return kokteli;
    }

    public void setKokteli(Set<KoktelSastojak> kokteli) {
        this.kokteli = kokteli;
    }

    @Override
    public String toString() {
        return "Sastojak{" +
                "id=" + id +
                ", naziv='" + naziv + '\'' +
                '}';
    }
}
