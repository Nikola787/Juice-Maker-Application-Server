package com.fpis.fontazija.kokteli.mapper.KoktelSastojak;

import com.fpis.fontazija.kokteli.dto.KoktelDetailsResponse;
import com.fpis.fontazija.kokteli.dto.KoktelsListResponse;
import com.fpis.fontazija.kokteli.entity.KoktelSastojak;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class KoktelSastojakMapper {

    public KoktelDetailsResponse toKoktelDetailsResponseDto(List<KoktelSastojak> koktelSastojaks) {
        KoktelDetailsResponse koktelDetailsResponse = new KoktelDetailsResponse(
                koktelSastojaks.getFirst().getKoktel().getId(),
                koktelSastojaks.getFirst().getKoktel().getNacinPripreme());

        koktelSastojaks.forEach(koktelSastojak ->
                koktelDetailsResponse.addSastojak(
                        koktelSastojak.getSastojak().getNaziv(),
                        String.valueOf(koktelSastojak.getKolicina()),
                        koktelSastojak.getMernaJedinica()));

        koktelDetailsResponse.setNaziv(koktelSastojaks.getFirst().getKoktel().getNaziv());
        koktelDetailsResponse.setOpis(koktelSastojaks.getFirst().getKoktel().getOpis());

        return koktelDetailsResponse;
    }


}
