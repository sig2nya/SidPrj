package smsf.avt_web.dao;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;

public class GenXmlRequest {
    HashMap<String, String> data;
    HashMap<String, String> scriptData;

    public HashMap<String, String> getData() {
        return data;
    }

    public void setData(HashMap<String, String> data) {
        this.data = data;
    }

    public HashMap<String, String> getScriptData() {
        return scriptData;
    }

    public void setScriptData(HashMap<String, String> scriptData) {
        this.scriptData = scriptData;
    }
}
