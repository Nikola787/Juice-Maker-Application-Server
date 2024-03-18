package com.fpis.fontazija.kokteli.service;

import com.fpis.fontazija.kokteli.dto.KoktelFilterRequest;
import com.fpis.fontazija.kokteli.dto.KoktelsListResponse;
import com.fpis.fontazija.kokteli.dto.PaginatedKoktelListResponse;
import com.fpis.fontazija.kokteli.entity.Koktel;
import com.fpis.fontazija.kokteli.dto.KoktelCreationRequest;

import java.util.List;

public interface IKoktelService {

    List<Koktel> getAllKoktels();

    Koktel addNewKoktel(Koktel koktel);

    String addNewKoktelCasaKoktelSastojciSastojci(KoktelCreationRequest koktelCreationRequest);


    PaginatedKoktelListResponse getKoktelsByFilters(KoktelFilterRequest koktelFilterRequest);

}
