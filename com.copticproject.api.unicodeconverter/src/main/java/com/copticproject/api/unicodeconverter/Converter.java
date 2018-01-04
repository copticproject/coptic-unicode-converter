package com.copticproject.api.unicodeconverter;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class Converter {
    private Map<String, Map<Character, String>> tables;

    public Converter() {
        this.tables = importConversionTables();
    }

    private Map<String, Map<Character, String>> importConversionTables() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            File jsonFile = new File(getClass().getClassLoader().getResource("conversion-tables.json").getFile());
            return mapper.readValue(jsonFile, Map<String, Map<Character, String>>.class);
        } catch (IOException e) {
            throw new RuntimeException("Error reading resources");
        }
    }
}
