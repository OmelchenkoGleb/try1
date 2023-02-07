package diplom.try1.DAO;

import diplom.try1.CrudRepository.CrudAllData;
import diplom.try1.CrudRepository.CrudTeachers;
import diplom.try1.Model.Teachers;
import diplom.try1.Model.all_data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class BdDAO {

    @Autowired
    CrudAllData crudAllData;

    @Autowired
    CrudTeachers crudTeachers;

    public void savedatalist(ArrayList<all_data> datalist){
        crudAllData.saveAll(datalist);
    }

    public void savedataTeacherslist(ArrayList<Teachers> datalist){
        crudTeachers.saveAll(datalist);
    }

    public Iterable getAllData(){return crudAllData.findAll();}

    public Iterable getSemestr(double semestr){return crudAllData.findAllBySemestr(semestr);}

    public Iterable getSemestrAndNullTeacher(Teachers teacher, double semestr){return crudAllData.findAllByTeachersAndSemestr(teacher,semestr);}

    public Iterable getTeachers(){return crudTeachers.findAll();}

    public void updateAllData(Long idalldate, Long idteachers) {
        all_data allData = crudAllData.findById(idalldate).get();
        Teachers teacher = crudTeachers.findById(idteachers).get();
        allData.setTeachers(teacher);
        crudAllData.save(allData);
    }
}
