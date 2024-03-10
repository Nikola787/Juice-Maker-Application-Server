package com.fpis.fontazija.kokteli.service;

import com.fpis.fontazija.kokteli.entity.*;
import com.fpis.fontazija.kokteli.repository.KoktelSastojakRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KoktelSastojakService implements IKoktelSastojakService{

    private KoktelSastojakRepository koktelSastojakRepository;

    public KoktelSastojakService(KoktelSastojakRepository koktelSastojakRepository){
        this.koktelSastojakRepository = koktelSastojakRepository;
    }

    @Override
    public List<KoktelSastojak> getAllKoktelSastojaks() {
        return koktelSastojakRepository.findAll();
    }

    @Override
    public KoktelSastojak addNewKoktelSastojak(KoktelSastojak koktelSastojak) {
        return koktelSastojakRepository.save(koktelSastojak);
    }

    @Override
    public List<KoktelSastojak> findById(int koktelId) {
        return koktelSastojakRepository.findById_KoktelId(koktelId);
    }


}
