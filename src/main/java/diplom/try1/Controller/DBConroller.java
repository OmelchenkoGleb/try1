package diplom.try1.Controller;


import diplom.try1.DAO.BdDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DBConroller {

    @Autowired
    BdDAO bdDAO;

    @GetMapping("/alldata")
    public String allData(Model model){
        model.addAttribute("data1", bdDAO.getSemestr(1));
        model.addAttribute("data2", bdDAO.getSemestr(2));
        return "/alldata";
    }
}
