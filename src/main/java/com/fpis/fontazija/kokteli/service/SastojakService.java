package com.fpis.fontazija.kokteli.service;

import com.fpis.fontazija.kokteli.entity.Koktel;
import com.fpis.fontazija.kokteli.entity.Sastojak;
import com.fpis.fontazija.kokteli.repository.SastojakRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SastojakService implements ISastojakService{

    private SastojakRepository sastojakRepository;

    @Autowired
    public SastojakService(SastojakRepository sastojakRepository){
        this.sastojakRepository = sastojakRepository;
    }


    @Override
    public List<Sastojak> getAllSastojaks() {
        return null;
    }

    @Override
    public Sastojak addNewSastojak(Sastojak sastojak) {
        return sastojakRepository.save(sastojak);
    }


}
