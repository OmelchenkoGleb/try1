package diplom.try1.Controller;

import ch.qos.logback.core.model.Model;
import diplom.try1.DAO.ExelParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class DExel {

    @Autowired
    ExelParser exelParser;

    @GetMapping("/")
    public String start(Model model){


        return "/index";
    }

    @PostMapping("/load")
    public String load(Model model, @RequestParam MultipartFile file, @RequestParam int sem11,
                       @RequestParam int sem12, @RequestParam int sem21, @RequestParam int sem22){

        exelParser.parser(file, sem11, sem12, sem21, sem22);

        return "/index";
    }
}