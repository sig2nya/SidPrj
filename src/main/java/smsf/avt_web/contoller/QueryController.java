package smsf.avt_web.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.context.request.WebRequest;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

@Controller
public class QueryController {
    @Value("${scen.path}")
    private String scenPath;
    @Value("${pkg.path}")
    private String pkgPath;

    @Value("${ipc.path}")
    private String ipcPath;

    @Autowired
    private WebRequest webRequest;

    @GetMapping("/scenList")
    public String scenList(Model model) {

        ArrayList<String> files = new ArrayList<>();

        try {
            Files.walkFileTree(Paths.get(scenPath), EnumSet.noneOf(FileVisitOption.class), 1, new SimpleFileVisitor<Path>() {
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    String fileName = file.getFileName().toString();
                    files.add(fileName);
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        model.addAttribute("files", files);
        return "avtList/scenList";
    }

    @GetMapping("/pkgList")
    public String pkgList(Model model) {

        ArrayList<String> files = new ArrayList<>();

        try {
            Files.walkFileTree(Paths.get(pkgPath), EnumSet.noneOf(FileVisitOption.class), 1, new SimpleFileVisitor<Path>() {
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    String fileName = file.getFileName().toString();
                    files.add(fileName);
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        model.addAttribute("files", files);
        return "avtList/pkgList";
    }

    @GetMapping("/initScen")
    public String initScen(Model model) {

        return "genAvt/initScen";
    }

    @GetMapping("/genScen")
    public String genScen(Model model) {
        ArrayList<String> ipcList = new ArrayList<>();

        try (BufferedReader reader = Files.newBufferedReader(Paths.get(ipcPath), StandardCharsets.UTF_8)) {
            String line;
            HashMap<String, List<String>> ipcMap = new HashMap<>();

            while ((line = reader.readLine()) != null) {
                String tmp[] = line.split("\\s+");
                ipcList.add(tmp[0]);

                List<String> values = Arrays.asList(Arrays.copyOfRange(tmp, 1, tmp.length));
                ipcMap.put(tmp[0], values);
                model.addAttribute("ipcMap", ipcMap);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        model.addAttribute("webRequest", webRequest);
        model.addAttribute("ipcList", ipcList);
        return "genAvt/genScen";
    }
}
