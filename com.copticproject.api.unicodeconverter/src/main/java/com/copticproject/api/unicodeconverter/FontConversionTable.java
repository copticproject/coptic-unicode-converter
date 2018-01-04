package com.copticproject.api.unicodeconverter;

import java.util.Map;

public class FontConversionTable {
    private String fontName;
    private Map<Integer, String> conversionTable;

    public String getFontName() {
        return fontName;
    }

    public void setFontName(String fontName) {
        this.fontName = fontName;
    }

    public Map<Integer, String> getConversionTable() {
        return conversionTable;
    }

    public void setConversionTable(Map<Integer, String> conversionTable) {
        this.conversionTable = conversionTable;
    }
}
