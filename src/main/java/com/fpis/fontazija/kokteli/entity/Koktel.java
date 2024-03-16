package com.fpis.fontazija.kokteli.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Set;


@Entity
@Table(name = "koktel")
public class Koktel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "naziv")
    @NotNull(message = "Value naziv Koktela cannot be null")
    @NotEmpty(message = "Value naziv Koktela cannot be empty")
    @Size(min = 2, max = 50, message = "Value naziv Koktela must be between 2 and 50 characters long")
    private String naziv;

    @Column(name = "opis")
    @NotNull(message = "Value opis koktela cannot be null")
    @NotEmpty(message = "Value opis koktela cannot be empty")
    @Size(min = 50, max = 200, message = "Value opis must have more than 50 characters")
    private String opis;

    @Column(name = "nacin_pripreme")
    @NotNull(message = "Value nacinPripreme koktela cannot be null")
    @NotEmpty(message = "Value nacinPripreme koktela cannot be empty")
    @Size(min = 50, max = 200, message = "Value nacinPripreme koktela must have more than 50 characters")
    private String nacinPripreme;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "casa_id")
    @NotNull
    @Valid
    private Casa casa;

    @ManyToOne()
    @JoinColumn(name = "kategorija_id")
    @NotNull
    @Valid
    private Kategorija kategorija;

    @OneToMany(mappedBy = "koktel")
    private Set<KoktelSastojak> sastojci;

    public Koktel() {}

    public Koktel(String naziv, String opis, String nacinPripreme, Casa casa, Kategorija kategorija) {
        this.naziv = naziv;
        this.opis = opis;
        this.nacinPripreme = nacinPripreme;
        this.casa = casa;
        this.kategorija = kategorija;
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

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public String getNacinPripreme() {
        return nacinPripreme;
    }

    public void setNacinPripreme(String nacinPripreme) {
        this.nacinPripreme = nacinPripreme;
    }

    public Casa getCasa() {
        return casa;
    }

    public void setCasa(Casa casa) {
        this.casa = casa;
    }

    public Kategorija getKategorija() {
        return kategorija;
    }

    public void setKategorija(Kategorija kategorija) {
        this.kategorija = kategorija;
    }


    public Set<KoktelSastojak> getSastojci() {
        return sastojci;
    }

    public void setSastojci(Set<KoktelSastojak> sastojci) {
        this.sastojci = sastojci;
    }

    @Override
    public String toString() {
        return "Koktel{" +
                "id=" + id +
                ", naziv='" + naziv + '\'' +
                ", opis='" + opis + '\'' +
                ", nacinPripreme='" + nacinPripreme + '\'' +
                ", casa=" + casa +
                ", kategorija=" + kategorija +
                '}';
    }
}
