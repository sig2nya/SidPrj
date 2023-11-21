package smsf.avt_web.xmlElements;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.Date;
import java.util.List;

@XmlRootElement
public class Scene {
    private int id;
    private String title;
    private String description;
    private Date date;
    private List<Flow> flow_list;

    @XmlElement
    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    @XmlElement
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @XmlElement
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @XmlElement
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @XmlElement
    public List<Flow> getFlowList() {
        return flow_list;
    }

    public void setFlowList(List<Flow> flow_list) {
        this.flow_list = flow_list;
    }
}
