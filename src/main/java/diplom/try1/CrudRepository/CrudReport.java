package diplom.try1.CrudRepository;

import diplom.try1.Model.report;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface CrudReport extends CrudRepository<report,Long> {
    ArrayList<report> findAllByName(String name);
}
