package com.copticproject.api.unicodeconverter;

import java.util.List;
import java.util.Map;

public class FontConversionTable {
    private Map<Character, String> conversionTable;
    private List<Character> swap;

    public Map<Character, String> getConversionTable() {
        return conversionTable;
    }

    public void setConversionTable(Map<Character, String> conversionTable) {
        this.conversionTable = conversionTable;
    }

    public List<Character> getSwap() {
        return swap;
    }

    public void setSwap(List<Character> swap) {
        this.swap = swap;
    }
}
