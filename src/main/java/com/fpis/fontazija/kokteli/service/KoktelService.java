package com.fpis.fontazija.kokteli.service;

import com.fpis.fontazija.kokteli.entity.Casa;
import com.fpis.fontazija.kokteli.entity.Kategorija;
import com.fpis.fontazija.kokteli.entity.Koktel;
import com.fpis.fontazija.kokteli.entity.KoktelSastojak;
import com.fpis.fontazija.kokteli.repository.KoktelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KoktelService implements IKoktelService{

   private final KoktelRepository koktelRepository;

   @Autowired
   public KoktelService(KoktelRepository theKoktelRepository){
       koktelRepository = theKoktelRepository;
   }


    @Override
    public List<Koktel> getAllKoktels() {
        return koktelRepository.findAll();
    }

    @Override
    public Koktel addNewKoktel(Koktel koktel) {
        return koktelRepository.save(koktel);
    }
}
