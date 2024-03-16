package com.fpis.fontazija.kokteli.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class KategorijaTest {

    private Kategorija kategorija;
    @BeforeEach
    public void init() {
        kategorija = new Kategorija();
    }

    @Test
    public void testSetId(){
        int id = 1;
        kategorija.setId(1);
        assertEquals(id, kategorija.getId());
    }

    @Test
    public void testSetNazivKategorije(){
        String name = "Sladak";
        kategorija.setNazivKategorije(name);
        assertEquals(name, kategorija.getNazivKategorije());
    }


}
