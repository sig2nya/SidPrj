package smsf.avt_web.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.context.request.WebRequest;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

@Controller
public class QueryController {
    @Value("${scen.path}")
    private String scenPath;
    @Value("${pkg.path}")
    private String pkgPath;

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
}
