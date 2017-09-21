package tk.avabin.tdg.beans.services.entities;

import tk.avabin.tdg.beans.entities.RPGSession;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Avabin on 09.04.2017.
 */
@Transactional
public interface RPGSessionService {
    RPGSession saveOrUpdate(RPGSession r);

    void delete(RPGSession r);

    RPGSession getById(int id);

    RPGSession getByName(String name);

    List getAll();

    boolean contains(String name);
}
