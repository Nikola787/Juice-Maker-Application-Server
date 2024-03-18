package com.fpis.fontazija.kokteli.service;

import com.fpis.fontazija.kokteli.dto.KoktelDetailsResponse;
import com.fpis.fontazija.kokteli.dto.KoktelFilterRequest;
import com.fpis.fontazija.kokteli.entity.KoktelSastojak;
import com.fpis.fontazija.kokteli.dto.KoktelsListResponse;

import java.util.List;

public interface IKoktelSastojakService {

    List<KoktelSastojak> getAllKoktelSastojaks();

    KoktelSastojak addNewKoktelSastojak(KoktelSastojak koktelSastojak);

    List<KoktelSastojak> findById(int koktelId);

    List<KoktelsListResponse> getAllKoktelsList();

    KoktelDetailsResponse findKoktelsById(int koktelId);
}
