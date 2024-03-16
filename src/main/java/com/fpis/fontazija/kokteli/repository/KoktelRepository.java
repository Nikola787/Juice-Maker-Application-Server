package com.fpis.fontazija.kokteli.repository;

import com.fpis.fontazija.kokteli.entity.Koktel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KoktelRepository extends JpaRepository<Koktel, Integer> {
    boolean existsByNaziv(String naziv);
}
