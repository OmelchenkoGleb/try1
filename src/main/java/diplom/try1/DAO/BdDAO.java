package diplom.try1.DAO;

import diplom.try1.CrudRepository.CrudAllData;
import diplom.try1.CrudRepository.CrudShablon;
import diplom.try1.CrudRepository.CrudTeachers;
import diplom.try1.Model.Teachers;
import diplom.try1.Model.all_data;
import diplom.try1.Model.shablon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class BdDAO {

    @Autowired
    CrudAllData crudAllData;
    @Autowired
    CrudTeachers crudTeachers;
    @Autowired
    CrudShablon crudShablon;

    public ArrayList<shablon> isShablon(){
        return (ArrayList<shablon>) crudShablon.findAll();
    }

    public void savedatalist(ArrayList<all_data> datalist){
        crudAllData.saveAll(datalist);
    }

    public void savedataTeacherslist(ArrayList<Teachers> datalist){
        crudTeachers.saveAll(datalist);
    }

    public Iterable getAllData(){return crudAllData.findAll();}

    public Iterable getSemestr(double semestr){return crudAllData.findAllBySemestrOrderByTeachers(semestr);}

    public Iterable getSemestrAndNullTeacher(Teachers teacher, double semestr){return crudAllData.findAllByTeachersAndSemestr(teacher,semestr);}

    public Iterable getSemestrAndTeacher(Long teacher, double semestr){return crudAllData.findAllByTeachersAndSemestr(crudTeachers.findById(teacher).get(),semestr);}

    public List<all_data> getSemestrAndTeacherList(Long teacher, double semestr){return (List<all_data>) crudAllData.findAllByTeachersAndSemestr(crudTeachers.findById(teacher).get(),semestr);}

    public Iterable getTeachers(){return crudTeachers.findAll();}

    public void updateAllData(Long idalldate, Long idteachers) {
        all_data allData = crudAllData.findById(idalldate).get();
        Teachers teacher = crudTeachers.findById(idteachers).get();
        allData.setTeachers(teacher);
        crudAllData.save(allData);
    }

    public void deleteAllData() {
        crudAllData.deleteAll();
    }

    public void deleteAllTeachers() {
        crudTeachers.deleteAll();
    }

    public void deleteOneData(Long id) {
        crudAllData.deleteById(id);
    }

    public void deleteOneTeacher(Long id) {
        crudTeachers.deleteById(id);
    }

    public Teachers findOneTeacher(Long id) {
        return crudTeachers.findById(id).get();
    }

    public all_data findOneData(Long id) {
        return crudAllData.findById(id).get();
    }

    public void updateTeacher(Teachers teachers) {
        crudTeachers.save(teachers);
    }

    public void updateOneData(all_data allData, Long idteachers) {
        Teachers teacher = crudTeachers.findById(idteachers).get();
        allData.setTeachers(teacher);
        crudAllData.save(allData);
    }

    public void saveShablon(MultipartFile file) throws IOException {
        shablon shablon = new shablon();
        shablon.setName(file.getOriginalFilename());
        shablon.setBytes(file.getBytes());
        crudShablon.save(shablon);
    }

    public void saveData(all_data allData) {
        crudAllData.save(allData);
    }
}
