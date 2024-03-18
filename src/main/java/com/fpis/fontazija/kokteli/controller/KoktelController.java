package com.fpis.fontazija.kokteli.controller;


import com.fpis.fontazija.kokteli.dto.*;
import com.fpis.fontazija.kokteli.entity.Koktel;
import com.fpis.fontazija.kokteli.service.KoktelSastojakService;
import com.fpis.fontazija.kokteli.service.KoktelService;
import com.fpis.fontazija.kokteli.service.SastojakService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLOutput;
import java.util.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class KoktelController {

    private KoktelService koktelService;
    private SastojakService sastojakService;
    private KoktelSastojakService koktelSastojakService;

    public KoktelController(KoktelService koktelService, SastojakService sastojakService, KoktelSastojakService koktelSastojakService){
        this.koktelService = koktelService;
        this.sastojakService = sastojakService;
        this.koktelSastojakService = koktelSastojakService;
    }

    @GetMapping("/koktel/{koktelId}")
    public ResponseEntity<KoktelDetailsResponse> getKoktelDetails(@PathVariable int koktelId) {
        return new ResponseEntity<>(
                koktelSastojakService.findKoktelsById(koktelId),
                HttpStatus.OK);
    }

    @PostMapping("/add/new-koktel")
    public ResponseEntity<String> addNewKoktel(@RequestBody KoktelCreationRequest koktelCreationRequest) {
        return new ResponseEntity<>(
                koktelService.addNewKoktelCasaKoktelSastojciSastojci(koktelCreationRequest),
                HttpStatus.OK);
    }

    @PostMapping("/koktels-filtered")
    public ResponseEntity<PaginatedKoktelListResponse> getKoktelsFiltered(@RequestBody KoktelFilterRequest koktelFilterRequest) {
        PaginatedKoktelListResponse koktelsListResponses = koktelService.getKoktelsByFilters(koktelFilterRequest);
        return new ResponseEntity<>(
                koktelsListResponses,
                HttpStatus.OK);
    }
}
