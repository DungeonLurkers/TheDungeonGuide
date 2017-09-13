package tk.avabin.tdg.beans.services.Entities;

import tk.avabin.tdg.beans.entities.Feat;

import java.util.List;

/**
 * Created by Avabin on 09.04.2017.
 */
public interface FeatService {
    void saveOrUpdate(Feat c);

    void delete(Feat c);

    Feat getById(int id);

    Feat getByName(String name);

    List getAll();
}
