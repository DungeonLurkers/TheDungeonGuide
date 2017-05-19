package tk.avabin.tdg.beans.Services.Entities;

import tk.avabin.tdg.beans.Entities.RPGClassAndLevel;

import java.util.List;

/**
 * Created by Avabin on 09.04.2017.
 */
public interface RPGClassAndLevelService {
    void saveOrUpdate(RPGClassAndLevel c);

    void delete(RPGClassAndLevel c);

    RPGClassAndLevel getById(int id);

    List getAll();
}
