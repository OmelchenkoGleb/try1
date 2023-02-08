package diplom.try1.Controller;


import diplom.try1.DAO.BdDAO;
import diplom.try1.Model.Teachers;
import diplom.try1.Model.all_data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping("/setteacher/{id}/{name}")
    public String setteacher(@PathVariable(value = "id") Long id, @PathVariable(value = "name") String name, Model model){
        model.addAttribute("alldataname", name);
        model.addAttribute("alldataid", id);
        model.addAttribute("data", bdDAO.getTeachers());
        return "/setteacher";
    }
    @PostMapping("/sett")
    public String setteacher(@RequestParam Long choose, @RequestParam Long idalldate, Model model){
        bdDAO.updateAllData(idalldate,choose);
        model.addAttribute("accept","Дані успішно додані");
        model.addAttribute("data1", bdDAO.getSemestrAndNullTeacher(null, 1));
        model.addAttribute("data2", bdDAO.getSemestrAndNullTeacher(null,2));
        return "/alldata";
    }

    @GetMapping("/all_deletealldata")
    public String all_deletealldata(Model model){
        bdDAO.deleteAllData();
        model.addAttribute("accept","Дані успішно видалені");
        return "/alldata";
    }

    @GetMapping("/all_deleteallteachers")
    public String all_deleteallteachers(Model model){
        bdDAO.deleteAllTeachers();
        model.addAttribute("accept","Дані успішно видалені");
        return "/teachers";
    }

    @GetMapping("/updateOneData/{id}")
    public String updateOneData(@PathVariable(value = "id") Long id, Model model){
        all_data allData = bdDAO.findOneData(id);
        model.addAttribute("allData", allData);
        return "/updatealldata";
    }

    @GetMapping("/deleteOneData/{id}")
    public String deleteOneData(@PathVariable(value = "id") Long id, Model model){
        System.out.println("222222222222");
        bdDAO.deleteOneData(id);
        model.addAttribute("accept","Дані успішно видалені");
        model.addAttribute("data1", bdDAO.getSemestrAndNullTeacher(null, 1));
        model.addAttribute("data2", bdDAO.getSemestrAndNullTeacher(null,2));
        return "/alldata";
    }

    @GetMapping("/updateOneTeacher/{id}")
    public String updateOneTeacher(@PathVariable(value = "id") Long id, Model model){
        Teachers teacher = bdDAO.findOneTeacher(id);
        model.addAttribute("teacher", teacher);
        return "/updateteacher";
    }

    @PostMapping("/updateOneTeacher")
    public String updateOneTeacherr(@ModelAttribute Teachers teachers, Model model){
        System.out.println(teachers);
        model.addAttribute("accept","Дані успішно змінені");
        model.addAttribute("data", bdDAO.getTeachers());
        return "/teachers";
    }


    @GetMapping("/deleteOneTeacher/{id}")
    public String deleteOneTeacher(@PathVariable(value = "id") Long id, Model model){
        bdDAO.deleteOneTeacher(id);
        model.addAttribute("accept","Дані успішно видалені");
        model.addAttribute("data", bdDAO.getTeachers());
        return "/teachers";
    }

}
