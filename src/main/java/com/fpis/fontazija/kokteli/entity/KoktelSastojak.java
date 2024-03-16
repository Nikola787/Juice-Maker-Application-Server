package com.fpis.fontazija.kokteli.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.util.Objects;

@Entity
@Table(name = "koktel_sastojak")
public class KoktelSastojak {

    @EmbeddedId
    KoktelSastojakKey id;

    @ManyToOne()
    @MapsId("koktelId")
    private Koktel koktel;

    @ManyToOne()
    @MapsId("sastojakId")
    private Sastojak sastojak;

    @Column(name = "kolicina")
    @Positive(message = "Value kolicina koktelSastojak must be greater than 0")
    private float kolicina;

    @Column(name = "merna_jedinica")
    @NotNull(message = "Value mernaJedinica koktelSastojak cannot be null")
    @NotEmpty(message = "Value mernaJedinica koktelSastojak cannot be empty")
    @Size(min = 2, message = "Value mernaJedinica koktelSastojak must have minimum 2 character")
    private String mernaJedinica;

    public KoktelSastojak() {}

    public KoktelSastojak(float kolicina, String mernaJedinica) {
        this.kolicina = kolicina;
        this.mernaJedinica = mernaJedinica;
    }

    public KoktelSastojak(Koktel koktel, Sastojak sastojak, float kolicina, String mernaJedinica) {
        this.id = new KoktelSastojakKey(koktel.getId(), sastojak.getId());
        this.koktel = koktel;
        this.sastojak = sastojak;
        this.kolicina = kolicina;
        this.mernaJedinica = mernaJedinica;
    }


    public KoktelSastojakKey getId() {
        return id;
    }

    public void setId(KoktelSastojakKey id) {
        this.id = id;
    }

    public Koktel getKoktel() {
        return koktel;
    }

    public void setKoktel(Koktel koktel) {
        if (this.sastojak != null) this.id = new KoktelSastojakKey(koktel.getId(), sastojak.getId());
        this.koktel = koktel;
    }

    public Sastojak getSastojak() {
        return sastojak;
    }

    public void setSastojak(Sastojak sastojak) {
        if (this.koktel != null) this.id = new KoktelSastojakKey(koktel.getId(), sastojak.getId());
        this.sastojak = sastojak;
    }

    public float getKolicina() {
        return kolicina;
    }

    public void setKolicina(float kolicina) {
        this.kolicina = kolicina;
    }

    public String getMernaJedinica() {
        return mernaJedinica;
    }

    public void setMernaJedinica(String mernaJedinica) {
        this.mernaJedinica = mernaJedinica;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KoktelSastojak that = (KoktelSastojak) o;
        return Float.compare(kolicina, that.kolicina) == 0 && Objects.equals(id, that.id) && Objects.equals(koktel, that.koktel) && Objects.equals(sastojak, that.sastojak) && Objects.equals(mernaJedinica, that.mernaJedinica);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, koktel, sastojak, kolicina, mernaJedinica);
    }

    @Override
    public String toString() {
        return "KoktelSastojak{" +
                "id=" + id +
                ", koktel=" + koktel +
                ", sastojak=" + sastojak +
                ", kolicina=" + kolicina +
                ", mernaJedinica='" + mernaJedinica + '\'' +
                '}';
    }
}
