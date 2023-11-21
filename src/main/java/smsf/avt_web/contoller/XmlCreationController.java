package smsf.avt_web.contoller;

import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import smsf.avt_web.dao.GenXMLDao;
import smsf.avt_web.xmlElements.Field;

import java.util.ArrayList;

@RestController
public class XmlCreationController {

    @PostMapping("/genScen/initScen")
    public static void initScen() {

    }

    @PostMapping("/genScen/addScen")
    public static void createScen(@RequestBody GenXMLDao genXMLDao) {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        ArrayList<String> textBoxValues = genXMLDao.getTextBoxValues();
        Field field = new Field();

        marshaller.setClassesToBeBound(Field.class);

        field.setName("TEST");

        for (String value : textBoxValues) {
            System.out.println(value);
        }
    }
}
