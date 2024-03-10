package com.fpis.fontazija.kokteli.repository;

import com.fpis.fontazija.kokteli.entity.KoktelSastojak;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface KoktelSastojakRepository extends JpaRepository<KoktelSastojak, Integer> {

    List<KoktelSastojak> findById_KoktelId(int koktelId);
}
