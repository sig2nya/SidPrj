package smsf.avt_web.xmlElements;

import jakarta.xml.bind.annotation.XmlElement;

public class Flow {
    private int id;
    private String requester;
    private String interf;
    private String message;
    private FieldList field_list;

    @XmlElement
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @XmlElement
    public String getInterf() {
        return interf;
    }

    public void setInterf(String interf) {
        this.interf = interf;
    }

    @XmlElement
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @XmlElement
    public FieldList getFieldList() {
        return field_list;
    }

    public void setFieldList(FieldList field_list) {
        this.field_list = field_list;
    }
}
