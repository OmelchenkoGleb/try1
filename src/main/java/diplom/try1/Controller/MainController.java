package diplom.try1.Controller;


import diplom.try1.DAO.BdDAO;
import diplom.try1.Model.shablon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;

@Controller
public class MainController {

    @Autowired
    BdDAO bdDAO;

    @GetMapping("/data")
    public String data(Model model){
        model.addAttribute("data1", bdDAO.getSemestr(1));
        model.addAttribute("data2", bdDAO.getSemestr(2));
        return "data";
    }
    @GetMapping("/")
    public String start(Model model){
        ArrayList<shablon> shablon = bdDAO.isShablon();
        if (shablon.size() == 0) {
            model.addAttribute("link", "link");
            model.addAttribute("data1", bdDAO.getSemestrAndNullTeacher(null, 1));
            model.addAttribute("data2", bdDAO.getSemestrAndNullTeacher(null,2));
            return "alldata";
        } else {
            model.addAttribute("data1", bdDAO.getSemestrAndNullTeacher(null, 1));
            model.addAttribute("data2", bdDAO.getSemestrAndNullTeacher(null,2));
            return "alldata";
        }

    }
    @GetMapping("/add_alldata")
    public String addalldata(Model model){
        return "index";
    }
    @GetMapping("/add_teachers")
    public String addteachers(Model model){
        return "addteachers";
    }

    @GetMapping("/all_delete")
    public String all_delete(Model model){
        return "all_delete";
    }

    @GetMapping("test")
    public String test(Model model){
        return "/test";
    }
}
