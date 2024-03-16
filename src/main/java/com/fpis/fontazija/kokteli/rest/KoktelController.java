package com.fpis.fontazija.kokteli.rest;


import com.fpis.fontazija.kokteli.dto.KoktelCreationRequest;
import com.fpis.fontazija.kokteli.dto.KoktelFilterRequest;
import com.fpis.fontazija.kokteli.dto.KoktelDetailsResponse;
import com.fpis.fontazija.kokteli.dto.KoktelsListResponse;
import com.fpis.fontazija.kokteli.service.KoktelSastojakService;
import com.fpis.fontazija.kokteli.service.KoktelService;
import com.fpis.fontazija.kokteli.service.SastojakService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

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

    @GetMapping("/test")
    public String testMetoda(@RequestParam("test") String test){
        return test;
    }

    @GetMapping("/koktel/{koktelId}")
    public ResponseEntity<KoktelDetailsResponse> getKoktelDetails(@PathVariable int koktelId) {
        KoktelDetailsResponse koktelDetailsResponse = koktelSastojakService.findKoktelsById(koktelId);
        return new ResponseEntity<>(koktelDetailsResponse, HttpStatus.OK);
    }

    @GetMapping("/koktels-list")
    public ResponseEntity<List<KoktelsListResponse>> getKoktelsList() {
        List<KoktelsListResponse> arr = koktelSastojakService.getAllKoktelsList();
        return new ResponseEntity<>(arr, HttpStatus.OK);
    }


    @PostMapping("/add/new-koktel")
    public ResponseEntity<String> addNewKoktel(@RequestBody KoktelCreationRequest koktelCreationRequest) {
        String response = koktelService.addNewKoktelCasaKoktelSastojciSastojci(koktelCreationRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/koktels-filtered")
    public ResponseEntity<List<KoktelsListResponse>> getKoktelsFiltered(@RequestBody KoktelFilterRequest koktelFilterRequest) {
        List<KoktelsListResponse> arr = koktelSastojakService.getKoktelsByFilters(koktelFilterRequest);
        return new ResponseEntity<>(arr, HttpStatus.OK);
    }



}
