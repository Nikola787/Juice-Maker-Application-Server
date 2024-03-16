package com.fpis.fontazija.kokteli.repository;

import com.fpis.fontazija.kokteli.entity.Sastojak;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SastojakRepository extends JpaRepository<Sastojak, Integer> {

    Sastojak findByNaziv(String naziv);
}
