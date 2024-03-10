package com.fpis.fontazija.kokteli.entity;


import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name="casa")
public class Casa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "naziv")
    private String naziv;

    @Column(name = "slika")
    private String slika;

    @OneToMany(mappedBy = "casa")
    private Set<Koktel> kokteli;

    public Casa() {}

    public Casa(String naziv, String slika) {
        this.naziv = naziv;
        this.slika = slika;
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

    public String getSlika() {
        return slika;
    }

    public void setSlika(String slika) {
        this.slika = slika;
    }

    public Set<Koktel> getKokteli() {
        return kokteli;
    }

    public void setKokteli(Set<Koktel> kokteli) {
        this.kokteli = kokteli;
    }

    @Override
    public String toString() {
        return "Casa{" +
                "id=" + id +
                ", naziv='" + naziv + '\'' +
                ", slika='" + slika + '\'' +
                '}';
    }

}
