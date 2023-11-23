package smsf.avt_web.xmlElements;

import jakarta.xml.bind.annotation.XmlElement;

public class Param {
    private int id;
    private String value;

    @XmlElement(name = "ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @XmlElement(name = "value")
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
