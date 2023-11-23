package smsf.avt_web.xmlElements;


import jakarta.xml.bind.annotation.XmlElement;

import java.util.List;

public class FlowList {
    private List<Flow> flows;

    @XmlElement(name = "Flow")
    public List<Flow> getFlows() {
        return flows;
    }

    public void setFlows(List<Flow> flows) {
        this.flows = flows;
    }
}
