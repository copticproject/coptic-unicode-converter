package com.copticproject.api.unicodeconverter;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class Converter {
    private Map<String, FontConversionTable> tables;

    public Converter() {
        this.tables = importConversionTables();
    }

    public String convert(String fontName, String sourceText) throws UnsupportedFontException {
        if (fontName.startsWith("CS"))
            fontName = "CS";
        if (!this.tables.containsKey(fontName))
            throw new UnsupportedFontException();
        FontConversionTable conversionTable = this.tables.get(fontName);
        Map<Character, String> charTable = conversionTable.getConversionTable();
        List<Character> swapList = conversionTable.getSwap();

        StringBuilder result = new StringBuilder();

        Stack<Character> swap = new Stack<Character>();

        for (char character : sourceText.toCharArray()) {
            if (swapList.contains(character))
                swap.push(character);
            else {
                append(charTable, result, character);
                while (!swap.isEmpty())
                    append(charTable, result, swap.pop());
            }
        }

        while (!swap.isEmpty())
            append(charTable, result, swap.pop());

        return result.toString();
    }

    private void append(Map<Character, String> charTable, StringBuilder result, char character) {
        if (!charTable.containsKey(character))
            result.append(character);
        else
            result.append(charTable.get(character));
    }

    private Map<String, FontConversionTable> importConversionTables() {
        try {
            ObjectMapper mapper = new ObjectMapper();

            TypeReference<HashMap<String, FontConversionTable>> type =
                    new TypeReference<HashMap<String, FontConversionTable>>() {};

            InputStream inputStream = getClass().getResourceAsStream("/conversion-tables.json");

            return mapper.readValue(inputStream, type);
        } catch (IOException e) {
            throw new RuntimeException("Error reading resources");
        }
    }
}
