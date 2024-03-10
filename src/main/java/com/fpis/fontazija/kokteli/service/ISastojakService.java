package com.fpis.fontazija.kokteli.service;

import com.fpis.fontazija.kokteli.entity.Sastojak;

import java.util.List;

public interface ISastojakService {

    List<Sastojak> getAllSastojaks();

    Sastojak addNewSastojak(Sastojak sastojak);
}
