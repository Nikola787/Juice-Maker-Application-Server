package com.fpis.fontazija.kokteli.service;

import com.fpis.fontazija.kokteli.entity.KoktelSastojak;

import java.util.List;

public interface IKoktelSastojakService {

    List<KoktelSastojak> getAllKoktelSastojaks();

    KoktelSastojak addNewKoktelSastojak(KoktelSastojak koktelSastojak);

    List<KoktelSastojak> findById(int koktelId);
}
