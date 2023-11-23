package smsf.avt_web.contoller;

import jakarta.servlet.http.HttpSession;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import smsf.avt_web.xmlElements.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Controller
public class XmlCreationController {
    @Value("${scen.path}")
    private String scenPath;

    @Value("${pkg.path}")
    private String pkgPath;

    @Value("${ipc.path}")
    private String ipcPath;

    @Value("${requester.path}")
    private String requesterPath;

    @Autowired
    private WebRequest webRequest;

    @GetMapping("/initScen")
    public String viewScen() {
        return "genAvt/initScen";
    }

    private static final JAXBContext context = initJAXBContenxt();

    private static JAXBContext initJAXBContenxt() {
        try {
            return JAXBContext.newInstance(Scene.class);
        } catch (JAXBException e) {
            e.printStackTrace();;
            throw new RuntimeException("Failed to initialize JAXBContext");
        }
    }

    @PostMapping("/initScen")
    public String initScen(@RequestBody Map<String, String> data, HttpSession session) {
        try {
            Scene scene = new Scene();
            scene.setId(1);
            scene.setTitle(data.get("nodeTitle"));
            scene.setDescription(data.get("description"));
            scene.setDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd")));

            FlowList flowList = new FlowList();
//            Flow flow = new Flow();
            List<Flow> list = new ArrayList<>();

//            list.add(flow);
            flowList.setFlows(list);
            scene.setFlowList(flowList);

            Marshaller marshaller = context.createMarshaller();

            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            StringWriter writer = new StringWriter();
            marshaller.marshal(scene, writer);

            File outputFile = new File(scenPath + "\\" + data.get("xmlName"));
            marshaller.marshal(scene, outputFile);
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        String filePath  = scenPath + "\\" + data.get("xmlName");
        session.setAttribute("filePath", filePath);
        return "genAvt/genScen";
    }

    @GetMapping("/genScen")
    public String genScen(Model model, HttpSession session) {
        ArrayList<String> ipcList = new ArrayList<>();
        ArrayList<String> requesterList = new ArrayList<>();

        // for ipcList
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(ipcPath), StandardCharsets.UTF_8)) {
            String line;
            HashMap<String, List<String>> ipcMap = new HashMap<>();

            while ((line = reader.readLine()) != null) {
                String tmp[] = line.split("\\s+");
                if (line == "") continue;
                ipcList.add(tmp[0]);

                List<String> values = Arrays.asList(Arrays.copyOfRange(tmp, 1, tmp.length));
                ipcMap.put(tmp[0], values);
                model.addAttribute("ipcMap", ipcMap);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // for reqeusterList
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(requesterPath), StandardCharsets.UTF_8)) {
            String line;

            while ((line = reader.readLine()) != null) {
                String tmp = line;
                if (line == "") continue;
                requesterList.add(tmp);

                model.addAttribute("requesterList", requesterList);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            String xmlContent = new String(Files.readAllBytes(Paths.get((String) session.getAttribute("filePath"))));
            model.addAttribute("xmlContent", xmlContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
        model.addAttribute("webRequest", webRequest);
        model.addAttribute("ipcList", ipcList);
        return "genAvt/genScen";
    }

    @PostMapping("/genScen")
    public static String createScen(@RequestBody Map<String, String> data, HttpSession session) {
        String xmlPath = (String) session.getAttribute("filePath");
        File xmlFile = new File(xmlPath);

        try {
            Unmarshaller unmarshaller = context.createUnmarshaller();
            Marshaller marshaller = context.createMarshaller();

            Scene scene = (Scene) unmarshaller.unmarshal(xmlFile);
            FlowList flowList = scene.getFlow_list();
            Flow flow = new Flow();

            FieldList fieldList = new FieldList();
            CheckList checkList = new CheckList();

            List<Field> fieldlist = new ArrayList<>();
            List<Check> checklist = new ArrayList<>();

            List<Flow> tmpFlowList = flowList.getFlows();

            flow.setId(tmpFlowList.size() + 1);
            flow.setMessage(data.get("IpcMsg"));
            flow.setRequester(data.get("Requester"));
            flow.setInterfaceName(data.get("Interface"));

            String keyTmp[] = data.keySet().toArray(new String[0]);
            String valueTmp[] = data.values().toArray(new String[0]);

            if (data.get("IpcMsg").contains("_RES")) {
                for (int i = 3; i < keyTmp.length; i++) {
                    Check check = new Check();
                    int id = keyTmp.length - 3;
                    check.setName(keyTmp[i]);
                    check.setId(id++);
                    check.setEnable(1);
                    check.setValue(valueTmp[i]);
                    checklist.add(check);
                }

                checkList.setChecks(checklist);
                flow.setCheckList(checkList);
                tmpFlowList.add(flow);
                flowList.setFlows(tmpFlowList);
                scene.setCheck_list(checkList);

            } else if (data.get("IpcMsg").contains("_REQ")){
                for (int i = 3; i < keyTmp.length; i++) {
                    Field field = new Field();
                    field.setName(keyTmp[i]);
                    field.setValue(valueTmp[i]);
                    fieldlist.add(field);
                }

                fieldList.setFields(fieldlist);
                flow.setFieldList(fieldList);
                tmpFlowList.add(flow);
                flowList.setFlows(tmpFlowList);
                scene.setFlowList(flowList);

            } else {
                System.out.println("Invalid IPC MSG!!!");
            }

            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(scene, xmlFile);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return "genAvt/genScen";
    }

    @GetMapping("/readXml")
    @ResponseBody
    public String readXml(HttpSession session) {
        String filePath  = (String) session.getAttribute("filePath");
        String xmlContent = "";

        try {
            xmlContent = new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return xmlContent;
    }
}
