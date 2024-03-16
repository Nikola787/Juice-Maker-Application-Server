package com.fpis.fontazija.kokteli.repository;

import com.fpis.fontazija.kokteli.entity.Kategorija;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KategorijaRepository extends JpaRepository<Kategorija, Integer> {
    Kategorija findIdByNazivKategorije(String nazivKategorije);
}
