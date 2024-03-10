package com.fpis.fontazija.kokteli.service;

import com.fpis.fontazija.kokteli.repository.KategorijaRepository;
import org.springframework.stereotype.Service;

@Service
public class KategorijaService {

    private KategorijaRepository kategorijaRepository;

    public KategorijaService(KategorijaRepository kategorijaRepository){
        this.kategorijaRepository = kategorijaRepository;
    }
}
