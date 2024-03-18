package com.fpis.fontazija.kokteli.controller;

import com.fpis.fontazija.kokteli.entity.Kategorija;
import com.fpis.fontazija.kokteli.service.KategorijaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class KategorijaController {

    private KategorijaService kategorijaService;

    public KategorijaController(KategorijaService kategorijaService) {
        this.kategorijaService = kategorijaService;
    }

    @GetMapping("/kategorija-list")
    public ResponseEntity<List<Kategorija>> getKategorijasList() {
        return new ResponseEntity<>(kategorijaService.getAllKategorijas(), HttpStatus.OK);
    }
}
