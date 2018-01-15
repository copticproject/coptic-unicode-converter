package com.copticproject.api.unicodeconverter.rest;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ConvertResponse {
    private String unicodeText;

    public String getUnicodeText() {
        return unicodeText;
    }

    public void setUnicodeText(String unicodeText) {
        this.unicodeText = unicodeText;
    }
}
