package com.fpis.fontazija.kokteli.service;

import com.fpis.fontazija.kokteli.entity.*;
import com.fpis.fontazija.kokteli.repository.KoktelSastojakRepository;
import com.fpis.fontazija.kokteli.dto.KoktelFilterRequest;
import com.fpis.fontazija.kokteli.dto.KoktelDetailsResponse;
import com.fpis.fontazija.kokteli.dto.KoktelsListResponse;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

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

    @Override
    public List<KoktelsListResponse> getAllKoktelsList() {

        Map<Integer, List<KoktelSastojak>> map = new HashMap<>();
        List<KoktelSastojak> koktelSastojaks = getAllKoktelSastojaks();
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

            String file_path = koktel.getCasa().getSlika();
            byte[] image;
            try {
                image = Files.readAllBytes(new File(file_path).toPath());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            KoktelsListResponse koktelsListResponse = new KoktelsListResponse(
                    koktel.getId(),
                    koktel.getNaziv(),
                    koktel.getKategorija().getNazivKategorije(),
                    image,
                    koktel.getCasa().getNaziv());
            for(int i = 0; i < el.getValue().size(); i++){
                koktelsListResponse.addSastojak(el.getValue().get(i).getSastojak().getNaziv(), String.valueOf(el.getValue().get(i).getKolicina()), el.getValue().get(i).getMernaJedinica());
            }
            arr.add(koktelsListResponse);
        }
        return arr;
    }


    public KoktelDetailsResponse findKoktelsById(int koktelId) {
        List<KoktelSastojak> koktelSastojaks = findById(koktelId);
        KoktelDetailsResponse koktelDetailsResponse = new KoktelDetailsResponse(koktelSastojaks.getFirst().getKoktel().getId(), koktelSastojaks.getFirst().getKoktel().getNacinPripreme());
        for(int i = 0; i < koktelSastojaks.size(); i++){
            koktelDetailsResponse.addSastojak(koktelSastojaks.get(i).getSastojak().getNaziv(), String.valueOf(koktelSastojaks.get(i).getKolicina()), koktelSastojaks.get(i).getMernaJedinica());
        }
        koktelDetailsResponse.setNaziv(koktelSastojaks.getFirst().getKoktel().getNaziv());
        koktelDetailsResponse.setOpis(koktelSastojaks.getFirst().getKoktel().getOpis());
        return koktelDetailsResponse;
    }

    public List<KoktelsListResponse> getKoktelsByFilters(KoktelFilterRequest koktelFilterRequest) {

        String search = koktelFilterRequest.getSearch();
        Set<String> kategorije = koktelFilterRequest.getKategorije();
        Set<String> sastojci = koktelFilterRequest.getSastojci();
        List<KoktelsListResponse> koktelsListResponses = getAllKoktelsList();

        List<KoktelsListResponse> res = new ArrayList<>();

        for(int i = 0; i < koktelsListResponses.size(); i++){

            if (kategorije.contains(koktelsListResponses.get(i).getNazivKategorije()) || kategorije.isEmpty()) {
                if (search == null || koktelsListResponses.get(i).getNazivKoktela().toLowerCase().startsWith(search.toLowerCase())) {

                    boolean sadrziTrenutni = false;
                    for (String naziv : sastojci) {
                        sadrziTrenutni = false;
                        for (int j = 0; j < koktelsListResponses.get(i).getSastojci().size(); j++) {
                            if (naziv.equals(koktelsListResponses.get(i).getSastojci().get(j).getFirst())) {
                                sadrziTrenutni = true;
                                break;
                            }
                        }
                        if (!sadrziTrenutni) break;
                    }
                    if (sadrziTrenutni){
                        res.add(koktelsListResponses.get(i));
                    }
                }
            }
        }

        for(int i = 0; i < koktelsListResponses.size(); i++){
            boolean sadrziTrenutni = true;
            if (kategorije.contains(koktelsListResponses.get(i).getNazivKategorije()) || kategorije.isEmpty()) {

                if (search == null || koktelsListResponses.get(i).getNazivKoktela().toLowerCase().startsWith(search.toLowerCase())) {

                    for (String naziv : sastojci) {
                        sadrziTrenutni = false;
                        for (int j = 0; j < koktelsListResponses.get(i).getSastojci().size(); j++) {
                            if (naziv.equals(koktelsListResponses.get(i).getSastojci().get(j).getFirst())) {
                                sadrziTrenutni = true;
                                break;
                            }
                        }
                        if (sadrziTrenutni) break;
                    }
                    if (sadrziTrenutni && !res.contains(koktelsListResponses.get(i))){
                        res.add(koktelsListResponses.get(i));
                    }
                }
            }
        }

        return res;
    }
}
