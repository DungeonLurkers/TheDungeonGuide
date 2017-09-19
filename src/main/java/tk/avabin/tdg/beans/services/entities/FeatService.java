package tk.avabin.tdg.beans.services.entities;

import tk.avabin.tdg.beans.entities.Feat;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Avabin on 09.04.2017.
 */
@Transactional
public interface FeatService {
    Feat saveOrUpdate(Feat c);

    void delete(Feat c);

    Feat getById(int id);

    Feat getByName(String name);

    List getAll();

    boolean contains(String name);
}
