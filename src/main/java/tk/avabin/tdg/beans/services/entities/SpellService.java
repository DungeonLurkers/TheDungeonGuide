package tk.avabin.tdg.beans.services.entities;

import tk.avabin.tdg.beans.entities.Spell;

import java.util.List;

/**
 * Created by Avabin on 09.04.2017.
 */
public interface SpellService {
    void saveOrUpdate(Spell s);

    void delete(Spell s);

    Spell getById(int id);

    Spell getByName(String name);

    List getAll();
}
