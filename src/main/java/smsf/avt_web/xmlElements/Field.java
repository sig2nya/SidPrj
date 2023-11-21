package smsf.avt_web.xmlElements;

import jakarta.xml.bind.annotation.XmlAttribute;

public class Field {
    private String name;

    @XmlAttribute(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
