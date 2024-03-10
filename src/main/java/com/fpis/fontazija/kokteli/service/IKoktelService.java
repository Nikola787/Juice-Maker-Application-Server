package com.fpis.fontazija.kokteli.service;

import com.fpis.fontazija.kokteli.entity.Koktel;
import com.fpis.fontazija.kokteli.entity.KoktelSastojak;

import java.util.List;

public interface IKoktelService {

    List<Koktel> getAllKoktels();

    Koktel addNewKoktel(Koktel koktel);

}
