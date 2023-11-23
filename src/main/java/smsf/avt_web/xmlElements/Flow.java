package smsf.avt_web.xmlElements;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class Flow {
    @XmlElement(name = "ID")
    private int id;
    @XmlElement(name = "Requester")
    private String requester;
    @XmlElement(name = "Interface")
    private String interfaceName;
    @XmlElement(name = "Message")
    private String message;
    @XmlElement(name = "Field_List")
    private FieldList field_list;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRequester() {
        return requester;
    }

    public void setRequester(String requester) {
        this.requester = requester;
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public FieldList getFieldList() {
        return field_list;
    }

    public void setFieldList(FieldList field_list) {
        this.field_list = field_list;
    }
}
