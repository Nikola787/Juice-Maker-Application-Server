package com.fpis.fontazija.kokteli.controller;

import com.fpis.fontazija.kokteli.entity.KoktelSastojak;
import com.fpis.fontazija.kokteli.repository.KoktelSastojakRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/koktelSastojaks")
public class KoktelSastojakController {

    private KoktelSastojakRepository koktelSastojakRepository;

    public KoktelSastojakController(KoktelSastojakRepository koktelSastojakRepository){
        this.koktelSastojakRepository = koktelSastojakRepository;
    }

    @GetMapping
    public List<KoktelSastojak> getKoktelSastojaks() {
        return koktelSastojakRepository.findAll();
    }



}
