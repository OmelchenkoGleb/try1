package diplom.try1.CrudRepository;

import diplom.try1.Model.Teachers;
import diplom.try1.Model.all_data;
import org.springframework.data.repository.CrudRepository;

public interface CrudAllData extends CrudRepository<all_data,Long> {
    Iterable<all_data> findAllBySemestr(double semestr);
    Iterable<all_data> findAllByTeachersAndSemestr(Teachers teachers, double semestr);



}
