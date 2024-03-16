package com.fpis.fontazija.kokteli.service;

import com.fpis.fontazija.kokteli.entity.Kategorija;
import com.fpis.fontazija.kokteli.repository.KategorijaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KategorijaService implements IKategorijaService{

    private KategorijaRepository kategorijaRepository;

    @Autowired
    public KategorijaService(KategorijaRepository kategorijaRepository){
        this.kategorijaRepository = kategorijaRepository;
    }


    @Override
    public List<Kategorija> getAllKategorijas() {
        return kategorijaRepository.findAll();
    }
}
