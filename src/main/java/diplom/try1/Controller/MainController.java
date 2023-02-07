package diplom.try1.Controller;


import diplom.try1.DAO.BdDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MainController {

    @Autowired
    BdDAO bdDAO;
    @GetMapping("/add")
    public String start(Model model){
        return "/add";
    }
    @GetMapping("/add_alldata")
    public String addalldata(Model model){
        return "/index";
    }
    @GetMapping("/add_teachers")
    public String addteachers(Model model){
        return "/addteachers";
    }

    @GetMapping("/setteacher/{id}/{name}")
    public String setteacher(@PathVariable(value = "id") Long id, @PathVariable(value = "name") String name, Model model){
        model.addAttribute("alldataname", name);
        model.addAttribute("alldataid", id);
        model.addAttribute("data", bdDAO.getTeachers());
        return "/setteacher";
    }
}
