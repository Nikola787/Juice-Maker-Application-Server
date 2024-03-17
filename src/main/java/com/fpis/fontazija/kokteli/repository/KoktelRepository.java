package com.fpis.fontazija.kokteli.repository;

import com.fpis.fontazija.kokteli.entity.Koktel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

public interface KoktelRepository extends JpaRepository<Koktel, Integer>{
    boolean existsByNaziv(String naziv);

    @Query(value = "SELECT " +
            "k.id AS idKoktela, " +
            "k.naziv AS nazivKoktela, " +
            "kat.naziv_kategorije AS nazivKategorije, " +
            "c.slika AS slikaCase, " +
            "c.naziv AS nazivCase, " +
            "GROUP_CONCAT(JSON_ARRAY(s.naziv, ks.kolicina, ks.merna_jedinica)) AS sastojci " +
            "FROM " +
            "koktel k " +
            "JOIN kategorija kat ON k.kategorija_id = kat.id " +
            "JOIN casa c ON k.casa_id = c.id " +
            "JOIN koktel_sastojak ks ON k.id = ks.koktel_id " +
            "JOIN sastojak s ON ks.sastojak_id = s.id " +
            "WHERE " +
            "(CASE WHEN :categoryArray IS NULL THEN TRUE ELSE kat.naziv_kategorije IN :categoryArray END) " +
            "AND (CASE WHEN :ingredientArray IS NULL THEN TRUE ELSE s.naziv IN :ingredientArray END) " +
            "AND (CASE WHEN :startingStringArray IS NULL THEN TRUE ELSE k.naziv LIKE CONCAT(:startingStringArray, '%') END) " +
            "GROUP BY " +
            "k.id, k.naziv, kat.naziv_kategorije, c.slika, c.naziv " +
            "ORDER BY " +
            "CASE WHEN COUNT(DISTINCT s.id) > 1 THEN 0 ELSE 1 END, " +
            "k.id", nativeQuery = true)
    List<Object[]> findKoktelsWithFilters(Set<String> categoryArray, Set<String> ingredientArray, String startingStringArray);
}
