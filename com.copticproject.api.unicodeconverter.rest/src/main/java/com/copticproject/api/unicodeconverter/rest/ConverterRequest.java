package com.copticproject.api.unicodeconverter.rest;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ConvertRequest {
    private String fontName;
    private String sourceText;

    public String getFontName() {
        return fontName;
    }

    public void setFontName(String fontName) {
        this.fontName = fontName;
    }

    public String getSourceText() {
        return sourceText;
    }

    public void setSourceText(String sourceText) {
        this.sourceText = sourceText;
    }
}
