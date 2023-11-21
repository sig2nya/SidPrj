package smsf.avt_web.contoller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

@Controller
public class RequestController {
    @PostMapping("/validationRequest/scen")
    public ResponseEntity<String> scenRequest(@RequestBody Map<String, Object> payload) throws IOException {
        String responseData = "Server Response : Success!";
        ObjectMapper objectMapper = new ObjectMapper();
        List<String> selectedValues = (List<String>) payload.get("selectedValues");
        String jsonString = objectMapper.writeValueAsString(selectedValues);

        Socket socket = new Socket("10.1.14.196", 28080);
        OutputStream outputStream = socket.getOutputStream();
        OutputStreamWriter writer = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);

        writer.write(jsonString);
        writer.flush();

        System.out.println("JSON : " + jsonString);

        outputStream.close();
        socket.close();

        return ResponseEntity.ok(responseData);
    }

    @PostMapping("/validationRequest/pkg")
    public ResponseEntity<String> pkgRequest(@RequestBody Map<String, Object> payload) throws IOException {
        String responseData = "Server Response : Success!";
        ObjectMapper objectMapper = new ObjectMapper();
        List<String> selectedValues = (List<String>) payload.get("selectedValues");
        String jsonString = objectMapper.writeValueAsString(selectedValues);

        Socket socket = new Socket("10.1.14.196", 28080);
        OutputStream outputStream = socket.getOutputStream();
        OutputStreamWriter writer = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);

        writer.write(jsonString);
        writer.flush();

        System.out.println("JSON : " + jsonString);

        outputStream.close();
        socket.close();

        return ResponseEntity.ok(responseData);
    }

}
