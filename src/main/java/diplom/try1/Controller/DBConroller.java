package diplom.try1.Controller;


import diplom.try1.DAO.BdDAO;
import diplom.try1.Model.Teachers;
import diplom.try1.Model.all_data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;

@Controller
public class DBConroller {

    @Autowired
    BdDAO bdDAO;

    @GetMapping("/alldata")
    public String allData(Model model){
        model.addAttribute("data1", bdDAO.getSemestrAndNullTeacher(null, 1));
        model.addAttribute("data2", bdDAO.getSemestrAndNullTeacher(null,2));
        return "alldata";
    }

    @GetMapping("/teachers")
    public String loadteachers(Model model){
        model.addAttribute("data", bdDAO.getTeachers());
        return "teachers";
    }

    @GetMapping("/setteacher/{id}/{name}")
    public String setteacher(@PathVariable(value = "id") Long id, @PathVariable(value = "name") String name, Model model){
        model.addAttribute("alldataname", name);
        model.addAttribute("alldataid", id);
        model.addAttribute("data", bdDAO.getTeachers());
        return "setteacher";
    }
    @PostMapping("/sett")
    public String setteacher(@RequestParam Long choose, @RequestParam Long idalldate, Model model){
        bdDAO.updateAllData(idalldate,choose);
        model.addAttribute("data1", bdDAO.getSemestrAndTeacher(choose, 1));
        model.addAttribute("data2", bdDAO.getSemestrAndTeacher(choose,2));
        model.addAttribute("teacher", bdDAO.findOneTeacher(choose));
        model.addAttribute("accept","Дані успішно змінені !");
        return "onedata";
    }

    @GetMapping("/all_deletealldata")
    public String all_deletealldata(Model model){
        bdDAO.deleteAllData();
        model.addAttribute("accept","Дані успішно видалені");
        return "alldata";
    }

    @GetMapping("/all_deleteallteachers")
    public String all_deleteallteachers(Model model){
        try {
            bdDAO.deleteAllTeachers();
            model.addAttribute("accept","Дані успішно видалені");
            return "teachers";
        } catch (Exception e){
            model.addAttribute("fail","Невдалося видалити дані, бо викладач прив'язан до предмета якогось. Спочатку треба відв'язати.");
            model.addAttribute("data", bdDAO.getTeachers());
            return "teachers";
        }
    }

    @GetMapping("/updateOneData/{id}")
    public String updateOneData(@PathVariable(value = "id") Long id, Model model){
        if (id == -1){
            all_data allData = new all_data();
            model.addAttribute("allData", allData);
            model.addAttribute("data", bdDAO.getTeachers());
            return "updatealldata";
        }
        all_data allData = bdDAO.findOneData(id);
        model.addAttribute("allData", allData);
        model.addAttribute("data", bdDAO.getTeachers());
        return "updatealldata";
    }

    @PostMapping("/updateOneData")
    public String updateOneDataa(@ModelAttribute all_data allData, @RequestParam Long choose, Model model){
        bdDAO.updateOneData(allData, choose);

        model.addAttribute("data1", bdDAO.getSemestrAndTeacher(choose, 1));
        model.addAttribute("data2", bdDAO.getSemestrAndTeacher(choose,2));
        model.addAttribute("teacher", bdDAO.findOneTeacher(choose));
        model.addAttribute("accept","Дані успішно змінені !");
        return "onedata";
    }

    @GetMapping("/deleteOneData/{id}")
    public String deleteOneData(@PathVariable(value = "id") Long id, Model model){
        bdDAO.deleteOneData(id);
        model.addAttribute("accept","Дані успішно видалені");
        model.addAttribute("data1", bdDAO.getSemestrAndNullTeacher(null, 1));
        model.addAttribute("data2", bdDAO.getSemestrAndNullTeacher(null,2));
        return "alldata";
    }

    @GetMapping("/deleteOneData/{id}/{idt}")
    public String deleteOneDat(@PathVariable(value = "id") Long id, @PathVariable(value = "idt") Long idt, Model model){
        bdDAO.deleteOneData(id);
        model.addAttribute("accept","Дані успішно видалені");
        model.addAttribute("teacher", bdDAO.findOneTeacher(idt));
        model.addAttribute("data1", bdDAO.getSemestrAndTeacher(idt, 1));
        model.addAttribute("data2", bdDAO.getSemestrAndTeacher(idt,2));
        return "onedata";
    }

    @GetMapping("/updateOneTeacher/{id}")
    public String updateOneTeacher(@PathVariable(value = "id") Long id, Model model){
        if (id == -1){
            Teachers teacher = new Teachers();
            model.addAttribute("teacher", teacher);
            return "updateteacher";
        }
        Teachers teacher = bdDAO.findOneTeacher(id);
        model.addAttribute("teacher", teacher);
        return "updateteacher";
    }

    @PostMapping("/updateOneTeacher")
    public String updateOneTeacherr(@ModelAttribute Teachers teachers, Model model){
        bdDAO.updateTeacher(teachers);
        model.addAttribute("accept","Дані успішно змінені");
        model.addAttribute("data", bdDAO.getTeachers());
        return "teachers";
    }


    @GetMapping("/deleteOneTeacher/{id}")
    public String deleteOneTeacher(@PathVariable(value = "id") Long id, Model model){
        try {
            bdDAO.deleteOneTeacher(id);
            model.addAttribute("accept","Дані успішно видалені");
            model.addAttribute("data", bdDAO.getTeachers());
            return "teachers";
        } catch (Exception e){
            model.addAttribute("fail","Невдалося видалити дані, бо викладач прив'язан до предмета якогось. Спочатку треба відв'язати.");
            model.addAttribute("data", bdDAO.getTeachers());
            return "teachers";
        }
    }


    @GetMapping("/choose")
    public String choose(Model model){
        model.addAttribute("data", bdDAO.getTeachers());
        return "choose";
    }
    @PostMapping("/onedata")
    public String onedata(@RequestParam Long choose,Model model){
        model.addAttribute("data1", bdDAO.getSemestrAndTeacher(choose, 1));
        model.addAttribute("data2", bdDAO.getSemestrAndTeacher(choose,2));
        model.addAttribute("teacher", bdDAO.findOneTeacher(choose));
        return "onedata";
    }

    @GetMapping("/download")
    public String download(Model model){
        model.addAttribute("data", bdDAO.getTeachers());
        return "download";
    }

    @GetMapping("/addshablon")
    public String shablon(Model model){
        return "shablon";
    }

    @PostMapping("/loadshablon")
    public String loadteachers(Model model, @RequestParam MultipartFile file) throws IOException {
        bdDAO.saveShablon(file);
        model.addAttribute("accept","Шаблон успішно додан !");
        model.addAttribute("data1", bdDAO.getSemestr(1));
        model.addAttribute("data2", bdDAO.getSemestr(2));
        return "alldata";
    }

    @GetMapping("/createCopy/{id}")
    public String createCopy(@PathVariable(value = "id") Long id, Model model) throws IllegalAccessException {
        all_data allData = bdDAO.findOneData(id);
        all_data newdata = new all_data();
        Class<all_data> all_dataClass = all_data.class;
        Field[] declaredFields = all_dataClass.getDeclaredFields();
        for (int i = 1; i<declaredFields.length-1; i++){
            declaredFields[i].setAccessible(true);
            if (i == 2) {
                String s = (String) declaredFields[i].get(allData);
                declaredFields[i].set(newdata,s);
            } else {
                declaredFields[i].setFloat(newdata,declaredFields[i].getFloat(allData));
            }
        }
        bdDAO.saveData(newdata);
        System.out.println(newdata);
        model.addAttribute("allData", newdata);
        return "updatealldata";
    }
}
