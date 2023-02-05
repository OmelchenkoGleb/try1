package diplom.try1.DAO;

import diplom.try1.Model.all_data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class BdDAO {
    public void savedatalist(ArrayList<all_data> datalist){
        datalist.forEach(System.out::println);
    }
}
