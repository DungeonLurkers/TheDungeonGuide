package tk.avabin.tdg.beans.Services.Entities;

import tk.avabin.tdg.beans.Entities.RPGSession;

import java.util.List;

/**
 * Created by Avabin on 09.04.2017.
 */
public interface RPGSessionService {
    void saveOrUpdate(RPGSession r);

    void delete(RPGSession r);

    RPGSession getById(int id);

    RPGSession getByName(String name);

    List getAll();
}
