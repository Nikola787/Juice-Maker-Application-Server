package com.fpis.fontazija.kokteli.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class KoktelSastojakKey implements Serializable {

    @Column(name = "koktel_id")
    private int koktelId;

    @Column(name = "sastojak_id")
    private int sastojakId;

    public KoktelSastojakKey() {}

    public KoktelSastojakKey(int koktelId, int sastojakId) {
        this.koktelId = koktelId;
        this.sastojakId = sastojakId;
    }

    public int getKoktelId() {
        return koktelId;
    }

    public void setKoktelId(int koktelId) {
        this.koktelId = koktelId;
    }

    public int getSastojakId() {
        return sastojakId;
    }

    public void setSastojakId(int sastojakId) {
        this.sastojakId = sastojakId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KoktelSastojakKey that = (KoktelSastojakKey) o;
        return koktelId == that.koktelId && sastojakId == that.sastojakId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(koktelId, sastojakId);
    }
}
