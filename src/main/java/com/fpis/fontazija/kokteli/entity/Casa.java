package com.fpis.fontazija.kokteli.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.Type;

import java.util.Set;

@Entity
@Table(name="casa")
public class Casa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "naziv")
    @NotNull(message = "Value naziv case cannot be null")
    @NotEmpty(message = "Value naziv case cannot be empty")
    @Size(min = 2, max = 50, message = "Glass name must be between 2 and 50 characters long")
    private String naziv;

    @Column(name = "slika")
    @Lob
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
