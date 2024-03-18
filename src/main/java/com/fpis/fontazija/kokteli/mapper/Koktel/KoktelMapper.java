package com.fpis.fontazija.kokteli.mapper.Koktel;

import com.fpis.fontazija.kokteli.conversion.CustomMultipartFile;
import com.fpis.fontazija.kokteli.dto.KoktelCreationRequest;
import com.fpis.fontazija.kokteli.dto.KoktelsListResponse;
import com.fpis.fontazija.kokteli.entity.Koktel;
import com.fpis.fontazija.kokteli.entity.KoktelSastojak;
import com.fpis.fontazija.kokteli.exceptions.ObjectNotValidException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class KoktelMapper {

    public List<KoktelsListResponse> toKoktelListResponsesDto(List<Koktel> koktels) {

        List<KoktelsListResponse> koktelsListResponses = new ArrayList<>();

        for(Koktel k : koktels){
            KoktelsListResponse koktelsListResponse = new KoktelsListResponse();
            koktelsListResponse.setIdKoktela(k.getId());
            koktelsListResponse.setNazivCase(k.getCasa().getNaziv());
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
    @Value("${folder.path}")
    private String FOLDER_PATH;


    public Koktel toKoktelEntity(KoktelCreationRequest koktelCreationRequest) {

        Koktel koktel = koktelCreationRequest.getKoktel();

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

        return koktel;
    }
}
