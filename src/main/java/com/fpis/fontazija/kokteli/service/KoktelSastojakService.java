package com.fpis.fontazija.kokteli.service;

import com.fpis.fontazija.kokteli.entity.*;
import com.fpis.fontazija.kokteli.mapper.KoktelSastojak.KoktelSastojakMapper;
import com.fpis.fontazija.kokteli.repository.KoktelSastojakRepository;
import com.fpis.fontazija.kokteli.dto.KoktelDetailsResponse;
import com.fpis.fontazija.kokteli.dto.KoktelsListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class KoktelSastojakService implements IKoktelSastojakService{

    private KoktelSastojakRepository koktelSastojakRepository;
    private KoktelSastojakMapper koktelSastojakMapper;

    @Autowired
    public KoktelSastojakService(KoktelSastojakRepository koktelSastojakRepository, KoktelSastojakMapper koktelSastojakMapper){
        this.koktelSastojakRepository = koktelSastojakRepository;
        this.koktelSastojakMapper = koktelSastojakMapper;
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


    @Override
    public KoktelDetailsResponse findKoktelsById(int koktelId) {
        return koktelSastojakMapper.toKoktelDetailsResponseDto(findById(koktelId));
    }

}
