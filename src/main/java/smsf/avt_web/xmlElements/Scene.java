package smsf.avt_web.xmlElements;

import jakarta.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Scene")
public class Scene {
    @XmlElement(name = "ID")
    private int id;
    @XmlElement(name = "Title")
    private String title;
    @XmlElement(name = "Description")
    private String description;
    @XmlElement(name = "Create_Date")
    private String date;
    @XmlElement(name = "Flow_List")
    private FlowList flow_list;

    public int getId() {
        return id;
    }
    public void setId(int id) { this.id = id; }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }


    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }

    public FlowList getFlow_list() {
        return flow_list;
    }
    public void setFlowList(FlowList flow_list) {
        this.flow_list = flow_list;
    }
}
