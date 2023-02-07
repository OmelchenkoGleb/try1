package diplom.try1.Controller;


import diplom.try1.DAO.BdDAO;
import diplom.try1.Model.Teachers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;

@Controller
public class DBConroller {

    @Autowired
    BdDAO bdDAO;

    @GetMapping("/alldata")
    public String allData(Model model){
        model.addAttribute("data1", bdDAO.getSemestrAndNullTeacher(null, 1));
        model.addAttribute("data2", bdDAO.getSemestrAndNullTeacher(null,2));
        return "/alldata";
    }

    @GetMapping("/teachers")
    public String loadteachers(Model model){
        model.addAttribute("data", bdDAO.getTeachers());
        return "/teachers";
    }

    @PostMapping("/sett")
    public String setteacher(@RequestParam Long choose, @RequestParam Long idalldate, Model model){
        System.out.println(choose+"   "+idalldate);
        model.addAttribute("accept","Дані успішно додані");
        return "/alldata";
    }

}
