package smsf.avt_web.xmlElements;

import jakarta.xml.bind.annotation.XmlElement;

import java.util.List;

public class CheckList {
    private List<Check> checks;

    @XmlElement(name = "Check_List")
    public List<Check> getChecks() {
        return checks;
    }

    public void setChecks(List<Check> checks) {
        this.checks = checks;
    }
}
