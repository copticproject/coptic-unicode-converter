package com.copticproject.api.unicodeconverter.tests;

import com.copticproject.api.unicodeconverter.Converter;
import org.junit.Test;
import static org.junit.Assert.*;

public class ConverterTest {
    @Test
    public void ConstructorTest() {
        Converter converter = new Converter();
    }

    @Test
    public void NormalTest() throws com.copticproject.api.unicodeconverter.UnsupportedFontException {
        Converter converter = new Converter();
        String result = converter.convert("CS", "HELLO");
        assertEquals("ϨⲈⲖⲖⲞ", result);
    }

    @Test
    public void ControlCharactersTest() throws com.copticproject.api.unicodeconverter.UnsupportedFontException {
        Converter converter = new Converter();
        String result = converter.convert("Athanasius", "H\nE\rL\tL O");
        assertEquals("Ⲏ\nⲈ\rⲖ\tⲖ Ⲟ", result);
    }

    @Test
    public void GenkimInAllFontsTest() throws com.copticproject.api.unicodeconverter.UnsupportedFontException {
        Converter converter = new Converter();
        assertEquals("Ϩ̀ⲈⲖⲖⲞ", converter.convert("CS", "`HELLO"));
        assertEquals("Ⲏ̀ⲈⲖⲖⲞ", converter.convert("Athanasius", "`HELLO"));
        assertEquals("Ϩ̀ⲈⲖⲖⲞ", converter.convert("Pishoi", ";HELLO"));
        //assertEquals("⳥ϨⲈⲖⲖⲞ", converter.convert("Antonious", "`HELLO")); // No Genkim in Antonious
        assertEquals("Ⲏ̀ⲈⲖⲖⲞ", converter.convert("Abraam", "`HELLO"));
        assertEquals("Ϩ̀ⲈⲖⲖⲞ", converter.convert("Avva Shenouda", "`HELLO"));
        assertEquals("Ϩ̀ⲈⲖⲖⲞ", converter.convert("New Athanasius", ";HELLO"));
        assertEquals("⳥ϨⲈⲖⲖⲞ", converter.convert("Nopher", "`HELLO"));
    }

    @Test
    public void ErrorFixTest() throws com.copticproject.api.unicodeconverter.UnsupportedFontException {
        Converter converter = new Converter();
        assertEquals("Ⲡⲉⲛⲓⲱⲧ  ⲉⲧ  ⲯⲉⲛ  ⲛⲓⲫϩⲟⲩⲓ̀  ⲙⲁⲣⲉϥⲧⲟⲩⲃⲟ  ⲛ̀ϫⲉ  ⲡⲉⲕⲣⲁⲛ. ⲙⲁⲣⲉⲥⲓ̀  ⲛ̀ϫⲉ  ⲧⲉⲕⲙⲉⲧⲟⲩⲣⲟ" +
                "  ⲡⲉⲧⲉ\\ⲛⲁⲕ  ⲙⲁⲣⲉϥϯⲱⲡⲓ  ⲙ̀ⲫ̀ⲣϩⲑ  ⲯⲉⲛ  ⲧ̀ⲫⲉ  ⲛⲉⲙ  \\ⲓϫⲉⲛ  ⲡⲓⲕⲁ\\ⲓ .", converter.convert("CS", "Peniwt  et  'en  nivhou`i  mareftoubo  `nje  pekran. marec`i  " +
                "`nje  tekmetouro  pete\\nak  maref]wpi  `m`vrh;  'en  `tve  nem  \\ijen  pika\\i ."));
    }

}