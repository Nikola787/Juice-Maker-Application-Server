package com.fpis.fontazija.kokteli.service;

import com.fpis.fontazija.kokteli.repository.CasaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CasaService {

    private CasaRepository casaRepository;

    @Autowired
    public CasaService(CasaRepository casaRepository){
        this.casaRepository = casaRepository;
    }
}
