package com.fpis.fontazija.kokteli.rest;


import com.fpis.fontazija.kokteli.entity.*;
import com.fpis.fontazija.kokteli.request.KoktelCreationRequest;
import com.fpis.fontazija.kokteli.response.KoktelDetailsResponse;
import com.fpis.fontazija.kokteli.response.KoktelsListResponse;
import com.fpis.fontazija.kokteli.service.KoktelSastojakService;
import com.fpis.fontazija.kokteli.service.KoktelService;
import com.fpis.fontazija.kokteli.service.SastojakService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api")
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
        List<KoktelSastojak> koktelSastojaks = koktelSastojakService.findById(koktelId);
        KoktelDetailsResponse koktelDetailsResponse = new KoktelDetailsResponse(koktelSastojaks.get(0).getKoktel().getId(), koktelSastojaks.getFirst().getKoktel().getNacinPripreme());
        for(int i = 0; i < koktelSastojaks.size(); i++){
            koktelDetailsResponse.addSastojak(koktelSastojaks.get(i).getSastojak().getNaziv(), String.valueOf(koktelSastojaks.get(i).getKolicina()), koktelSastojaks.get(i).getMernaJedinica());
        }
        return new ResponseEntity<>(koktelDetailsResponse, HttpStatus.OK);
    }

    @GetMapping("/koktels-list")
    public ResponseEntity<List<KoktelsListResponse>> getKoktelsList() {
        Map<Integer, List<KoktelSastojak>> map = new HashMap<>();
        List<KoktelSastojak> koktelSastojaks = koktelSastojakService.getAllKoktelSastojaks();
        for (int i = 0; i < koktelSastojaks.size(); i++) {
            KoktelSastojak koktelSastojak = koktelSastojaks.get(i);
            int koktelId = koktelSastojak.getKoktel().getId();
            if ( map.containsKey(koktelId) ){
                map.get(koktelId).add(koktelSastojak);
            }
            else {
                map.put(koktelId, new ArrayList<KoktelSastojak>(List.of(koktelSastojak)));
            }
        }
        List<KoktelsListResponse> arr = new ArrayList<>();
        for(Map.Entry<Integer, List<KoktelSastojak>> el : map.entrySet()){
            Koktel koktel = el.getValue().getFirst().getKoktel();
            KoktelsListResponse koktelsListResponse = new KoktelsListResponse(
                    koktel.getId(),
                    koktel.getNaziv(),
                    koktel.getKategorija().getNazivKategorije(),
                    koktel.getCasa().getSlika(),
                    koktel.getCasa().getNaziv());
            for(int i = 0; i < el.getValue().size(); i++){
                koktelsListResponse.addSastojak(el.getValue().get(i).getSastojak().getNaziv(), String.valueOf(el.getValue().get(i).getKolicina()), el.getValue().get(i).getMernaJedinica());
            }
            arr.add(koktelsListResponse);
        }

        return new ResponseEntity<>(arr, HttpStatus.OK);
    }


    @PostMapping("/add/new-koktel")
    public ResponseEntity<String> addNewKoktel(@RequestBody KoktelCreationRequest koktelCreationRequest) {

        Koktel koktel = koktelCreationRequest.getKoktel();
        Koktel savedKoktel = koktelService.addNewKoktel(koktel);

        List<Sastojak> sastojaks = koktelCreationRequest.getSastojci();
        List<KoktelSastojak> koktelSastojaks = koktelCreationRequest.getKoktelSastojaks();

        for(int i = 0; i < sastojaks.size(); i++){
            KoktelSastojak koktelSastojak = koktelSastojaks.get(i);
            koktelSastojak.setKoktel(savedKoktel);
            koktelSastojak.setSastojak(sastojakService.addNewSastojak(sastojaks.get(i)));
            KoktelSastojak savedKoktelSastojak = koktelSastojakService.addNewKoktelSastojak(koktelSastojak);
        }
        return new ResponseEntity<>("Uspesno sacuvan Koktel, tip Case i Lista Sastojaka!", HttpStatus.OK);
    }
}
