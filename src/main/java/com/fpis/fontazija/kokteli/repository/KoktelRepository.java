package com.fpis.fontazija.kokteli.repository;

import com.fpis.fontazija.kokteli.entity.Koktel;
import com.fpis.fontazija.kokteli.specification.KoktelSpecifications;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

public interface KoktelRepository extends JpaRepository<Koktel, Integer>, JpaSpecificationExecutor<Koktel>{
    boolean existsByNaziv(String naziv);
}
