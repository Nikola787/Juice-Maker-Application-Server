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

    public List<KoktelsListResponse> toKoktelListResponsesDto(List<KoktelSastojak> koktelSastojaks) {
        return koktelSastojaks.stream()
                .collect(Collectors.groupingBy(koktelSastojak -> koktelSastojak.getKoktel().getId()))
                .values().stream()
                .map(sastojaks -> {
                    KoktelSastojak firstKoktelSastojak = sastojaks.getFirst();
                    String filePath = firstKoktelSastojak.getKoktel().getCasa().getSlika();
                    byte[] image;
                    try {
                        image = Files.readAllBytes(new File(filePath).toPath());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                    KoktelsListResponse koktelsListResponse = new KoktelsListResponse(
                            firstKoktelSastojak.getKoktel().getId(),
                            firstKoktelSastojak.getKoktel().getNaziv(),
                            firstKoktelSastojak.getKoktel().getKategorija().getNazivKategorije(),
                            image,
                            firstKoktelSastojak.getKoktel().getCasa().getNaziv());

                    sastojaks.forEach(koktelSastojak ->
                            koktelsListResponse.addSastojak(
                                    koktelSastojak.getSastojak().getNaziv(),
                                    String.valueOf(koktelSastojak.getKolicina()),
                                    koktelSastojak.getMernaJedinica()
                            )
                    );
                    return koktelsListResponse;
                })
                .collect(Collectors.toList());
    }

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
