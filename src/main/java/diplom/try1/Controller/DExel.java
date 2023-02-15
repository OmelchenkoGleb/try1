package diplom.try1.Controller;


import diplom.try1.DAO.BdDAO;
import diplom.try1.DAO.ExelParser;
import diplom.try1.Model.Teachers;
import diplom.try1.Model.all_data;
import diplom.try1.service.MailSender;
import jakarta.mail.MessagingException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Controller
public class DExel {

    @Autowired
    ExelParser exelParser;

    @Autowired
    BdDAO bdDAO;

    @Autowired
    private MailSender mailSender;

    @PostMapping("/load")
    public String load(Model model, @RequestParam MultipartFile file, @RequestParam int sem11,
                       @RequestParam int sem12, @RequestParam int sem21, @RequestParam int sem22){
        ArrayList<all_data> datalist = exelParser.parser(file, sem11, sem12, sem21, sem22);
        bdDAO.savedatalist(datalist);
        model.addAttribute("data1", bdDAO.getSemestr(1));
        model.addAttribute("data2", bdDAO.getSemestr(2));
        model.addAttribute("accept","Дані успішно додані");
        return "/alldata";
    }

    @PostMapping("/loadteachers")
    public String loadteachers(Model model, @RequestParam MultipartFile file){

        ArrayList<Teachers> datalist = exelParser.parsertecher(file);
        bdDAO.savedataTeacherslist(datalist);
        model.addAttribute("accept","Дані успішно додані");
        model.addAttribute("data", bdDAO.getTeachers());
        return "/teachers";
    }

    @PostMapping("/download")
    public String download(HttpServletResponse response, @RequestParam Long choose, @RequestParam String download, Model model) throws IOException, MessagingException {
        Teachers teacher = bdDAO.findOneTeacher(choose);
        List<all_data> dataList1 = bdDAO.getSemestrAndTeacherList(choose,1);
        List<all_data> dataList2 = bdDAO.getSemestrAndTeacherList(choose,2);
        exelParser.download(teacher, dataList1, dataList2);

        File file = new File(teacher.getName() + ".xls");

        if (Objects.equals(download, "1")){
            try {
                response.setContentType("application/octet-stream");
                response.setHeader("Content-Disposition", "attachment; filename=" + teacher.getName());
                ServletOutputStream outputStream = response.getOutputStream();
                BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(file));
                byte[] buffer = new byte[1024];
                int bytesRead = 0;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
                inputStream.close();
                outputStream.close();
                if(file.delete()){
                    System.out.println("Файл видалений");
                }
                model.addAttribute("data", bdDAO.getTeachers());
                model.addAttribute("accept","Файл викачено успішно !");
                return "/download";
            } catch (Exception e){
                if(file.delete()){
                    System.out.println("Файл видалений");
                }
                model.addAttribute("data", bdDAO.getTeachers());
                model.addAttribute("accept","s !");
                return "/download";
            }
        } else {
            try {
                mailSender.sendMessageWithAttachment(teacher.getEmail(),"Сгенероване пед навантаження для "+teacher.getName(), "Прошу подивіться файл в закріплені для цього письма.\nЗ повагою Оксана Дацюк.", file.getName());
                if(file.delete()){
                    System.out.println("Файл видалений");
                }
                model.addAttribute("data", bdDAO.getTeachers());
                model.addAttribute("accept","Файл викачено успішно !");
                return "/download";
            } catch (Exception e) {
                if(file.delete()){
                    System.out.println("Файл видалений");
                }
                model.addAttribute("data", bdDAO.getTeachers());
                model.addAttribute("accept","s");
                return "/download";
            }

        }

    }
}
