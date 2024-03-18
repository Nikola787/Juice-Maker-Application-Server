package com.fpis.fontazija.kokteli.service;

import com.fpis.fontazija.kokteli.conversion.CustomMultipartFile;
import com.fpis.fontazija.kokteli.dto.KoktelFilterRequest;
import com.fpis.fontazija.kokteli.dto.KoktelsListResponse;
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
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Service
public class KoktelService implements IKoktelService{

   private final KoktelRepository koktelRepository;
   private final SastojakRepository sastojakRepository;
   private final KoktelSastojakRepository koktelSastojakRepository;

   private final KategorijaRepository kategorijaRepository;
   private final ObjectsValidator<KoktelCreationRequest> validator;
   private final String FOLDER_PATH = "C:\\Users\\Nikola\\Desktop\\Master\\fpis\\media\\";

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

        validator.validate(koktelCreationRequest);

        if (koktelRepository.existsByNaziv(koktelCreationRequest.getKoktel().getNaziv())) {
            throw new ObjectNotValidException(Set.of("Name of the Cocktail already exists in the database."));
        }

        Kategorija kategorija = kategorijaRepository.findIdByNazivKategorije(koktelCreationRequest.getKoktel().getKategorija().getNazivKategorije());

        if (kategorija == null){
            throw new ObjectNotValidException(Set.of("Name of the Kategorija must exists in the database"));
        }

        Koktel koktel = koktelCreationRequest.getKoktel();
        koktel.setKategorija(kategorija);

        if (koktelCreationRequest.getBase64Image() != null){

            String fullImage = koktelCreationRequest.getBase64Image();
            String base64 = fullImage.substring(fullImage.indexOf(",") + 1);
            String type = fullImage.substring(fullImage.indexOf("/") + 1, fullImage.indexOf(";"));

            if ( !type.equals("png") && !type.equals("jpeg") && !type.equals("gif")){
                throw new ObjectNotValidException(Set.of("Provided file is not an image."));
            }

            byte[] decodedBytes = Base64.getDecoder().decode(base64);

            MultipartFile image = new CustomMultipartFile(decodedBytes);
            String FILE_PATH = FOLDER_PATH + UUID.randomUUID().toString() + "." + type;

            koktel.getCasa().setSlika(FILE_PATH);

            try {
                image.transferTo(new File(FILE_PATH));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        addNewKoktel(koktel);
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
    public List<KoktelsListResponse> getKoktelsByFilters(KoktelFilterRequest koktelFilterRequest) {

       return koktelMapper.toKoktelListResponsesDto(
                koktelRepository.findAll(
                        new KoktelSpecifications(
                                koktelFilterRequest.getKategorije(),
                                koktelFilterRequest.getSastojci(),
                                koktelFilterRequest.getSearch()
                        )));
    }


}
