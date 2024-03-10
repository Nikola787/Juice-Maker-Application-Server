package com.fpis.fontazija.kokteli.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name="kategorija")
public class Kategorija {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "naziv_kategorije")
    private String nazivKategorije;

    @OneToMany(mappedBy = "kategorija")
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
