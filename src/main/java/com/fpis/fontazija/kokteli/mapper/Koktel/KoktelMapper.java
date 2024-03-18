package com.fpis.fontazija.kokteli.mapper.Koktel;

import com.fpis.fontazija.kokteli.dto.KoktelsListResponse;
import com.fpis.fontazija.kokteli.entity.Koktel;
import com.fpis.fontazija.kokteli.entity.KoktelSastojak;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class KoktelMapper {

    public List<KoktelsListResponse> toKoktelListResponsesDto(List<Koktel> koktels) {

        List<KoktelsListResponse> koktelsListResponses = new ArrayList<>();

        for(Koktel k : koktels){
            KoktelsListResponse koktelsListResponse = new KoktelsListResponse();
            koktelsListResponse.setIdKoktela(k.getId());
            koktelsListResponse.setNazivCase(k.getCasa().getNaziv());
            koktelsListResponse.setSastojci(new ArrayList<>(){});
            koktelsListResponse.setNazivKategorije(k.getKategorija().getNazivKategorije());
            koktelsListResponse.setNazivKoktela(k.getNaziv());

            String filePath = k.getCasa().getSlika();
            byte[] image;
            try {
                image = Files.readAllBytes(new File(filePath).toPath());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            koktelsListResponse.setSlikaCase(image);
            koktelsListResponses.add(koktelsListResponse);
        }
        return koktelsListResponses;
    }

}
