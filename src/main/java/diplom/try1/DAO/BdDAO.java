package diplom.try1.DAO;

import diplom.try1.CrudRepository.CrudAllData;
import diplom.try1.Model.all_data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class BdDAO {

    @Autowired
    CrudAllData crudAllData;

    public void savedatalist(ArrayList<all_data> datalist){
        crudAllData.saveAll(datalist);
    }

    public Iterable getAllData(){return crudAllData.findAll();}

    public Iterable getSemestr(double semestr){return crudAllData.findAllBySemestr(semestr);}
}
