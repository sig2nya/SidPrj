package smsf.avt_web.xmlElements;

import jakarta.xml.bind.annotation.XmlElement;

public class Check {
    private String name;
    private String value;
    private int paramNum;
    private String result;
    private Param param;

    @XmlElement(name = "Name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlElement(name = "Value")
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @XmlElement(name = "ParamNum")
    public int getParamNum() {
        return paramNum;
    }

    public void setParamNum(int paramNum) {
        this.paramNum = paramNum;
    }

    @XmlElement(name = "Result")
    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @XmlElement(name = "Param")
    public Param getParam() {
        return param;
    }

    public void setParam(Param param) {
        this.param = param;
    }
}
