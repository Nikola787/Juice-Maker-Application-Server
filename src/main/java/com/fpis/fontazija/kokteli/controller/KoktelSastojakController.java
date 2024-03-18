package com.fpis.fontazija.kokteli.controller;

import com.fpis.fontazija.kokteli.entity.KoktelSastojak;
import com.fpis.fontazija.kokteli.repository.KoktelSastojakRepository;
import com.fpis.fontazija.kokteli.service.KoktelSastojakService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class KoktelSastojakController {

    private KoktelSastojakService koktelSastojakService;

    @Autowired
    public KoktelSastojakController(KoktelSastojakService koktelSastojakService){
        this.koktelSastojakService = koktelSastojakService;
    }

}
