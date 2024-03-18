package com.fpis.fontazija.kokteli.service;

import com.fpis.fontazija.kokteli.dto.KoktelFilterRequest;
import com.fpis.fontazija.kokteli.dto.KoktelsListResponse;
import com.fpis.fontazija.kokteli.dto.PaginatedKoktelListResponse;
import com.fpis.fontazija.kokteli.entity.*;
import com.fpis.fontazija.kokteli.exceptions.ObjectNotValidException;
import com.fpis.fontazija.kokteli.mapper.Koktel.KoktelMapper;
import com.fpis.fontazija.kokteli.repository.KategorijaRepository;
import com.fpis.fontazija.kokteli.repository.KoktelRepository;
import com.fpis.fontazija.kokteli.repository.KoktelSastojakRepository;
import com.fpis.fontazija.kokteli.repository.SastojakRepository;
import com.fpis.fontazija.kokteli.dto.KoktelCreationRequest;
import com.fpis.fontazija.kokteli.specification.KoktelSpecifications;
import com.fpis.fontazija.kokteli.validators.ObjectsValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class KoktelService implements IKoktelService{

   private final KoktelRepository koktelRepository;
   private final SastojakRepository sastojakRepository;
   private final KoktelSastojakRepository koktelSastojakRepository;

   private final KategorijaRepository kategorijaRepository;
   private final ObjectsValidator<KoktelCreationRequest> validator;
   @Value("${folder.path}")
   private String FOLDER_PATH;

   private final KoktelMapper koktelMapper;

   @Autowired
   public KoktelService(KoktelRepository theKoktelRepository, SastojakRepository sastojakRepository, KoktelSastojakRepository koktelSastojakRepository, KategorijaRepository kategorijaRepository, ObjectsValidator validator, KoktelMapper koktelMapper){
       this.koktelRepository = theKoktelRepository;
       this.sastojakRepository = sastojakRepository;
       this.koktelSastojakRepository = koktelSastojakRepository;
       this.kategorijaRepository = kategorijaRepository;
       this.validator = validator;
       this.koktelMapper = koktelMapper;
   }


    @Override
    public List<Koktel> getAllKoktels() {
        return koktelRepository.findAll();
    }

    @Override
    public Koktel addNewKoktel(Koktel koktel) {
        return koktelRepository.save(koktel);
    }

    @Override
    public String addNewKoktelCasaKoktelSastojciSastojci(KoktelCreationRequest koktelCreationRequest) {

       // Validations
        validator.validate(koktelCreationRequest);
        if (koktelRepository.existsByNaziv(koktelCreationRequest.getKoktel().getNaziv()))
            throw new ObjectNotValidException(Set.of("Name of the Cocktail already exists in the database."));
        Kategorija kategorija = kategorijaRepository.findIdByNazivKategorije(koktelCreationRequest.getKoktel().getKategorija().getNazivKategorije());
        if (kategorija == null)
            throw new ObjectNotValidException(Set.of("Name of the Kategorija must exists in the database"));

        // Mapping Dto to Koktel entity
        Koktel koktel = koktelMapper.toKoktelEntity(koktelCreationRequest);
        koktel.setKategorija(kategorija);
        addNewKoktel(koktel);

        // Saving KoktelSastojak and Sastojak entities
        List<Sastojak> sastojaks = koktelCreationRequest.getSastojci();
        List<KoktelSastojak> koktelSastojaks = koktelCreationRequest.getKoktelSastojaks();
        for(int i = 0; i < sastojaks.size(); i++){
            KoktelSastojak koktelSastojak = koktelSastojaks.get(i);
            koktelSastojak.setKoktel(koktel);
            Sastojak existingSastojak = sastojakRepository.findByNaziv(sastojaks.get(i).getNaziv());
            if (existingSastojak != null ){
                koktelSastojak.setSastojak(existingSastojak);
            } else {
                koktelSastojak.setSastojak(sastojakRepository.save(sastojaks.get(i)));
            }
            koktelSastojakRepository.save(koktelSastojak);
        }

        return "Successfully saved entities!";
    }


    @Override
    public PaginatedKoktelListResponse getKoktelsByFilters(KoktelFilterRequest koktelFilterRequest) {
       Page<Koktel> page = koktelRepository.findAll(
               new KoktelSpecifications(
                              koktelFilterRequest.getKategorije(),
                              koktelFilterRequest.getSastojci(),
                              koktelFilterRequest.getSearch()
                      ),
               PageRequest.of(koktelFilterRequest.getOffset(), koktelFilterRequest.getPageSize()));
       List<Koktel> koktels = page.getContent();
       int totalPages = page.getTotalPages();
       List<KoktelsListResponse> koktelsListResponses = koktelMapper.toKoktelListResponsesDto(koktels);
       PaginatedKoktelListResponse paginatedKoktelListResponse = new PaginatedKoktelListResponse(koktelsListResponses, totalPages);
       return paginatedKoktelListResponse;
    }

}
