package tk.avabin.tdg.beans.services.entities;

import tk.avabin.tdg.beans.entities.RPGClassAndLevel;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Avabin on 09.04.2017.
 */
@Transactional
public interface RPGClassAndLevelService extends IEntityService {
    RPGClassAndLevel saveOrUpdate(RPGClassAndLevel c);

    void delete(RPGClassAndLevel c);

    RPGClassAndLevel getById(int id);

    List getAll();

    boolean contains(String name);
}
