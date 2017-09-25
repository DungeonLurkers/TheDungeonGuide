package tk.avabin.tdg.beans.services.entities;

import tk.avabin.tdg.beans.entities.Spell;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Avabin on 09.04.2017.
 */
@Transactional
public interface SpellService {
    Spell saveOrUpdate(Spell s);

    void delete(Spell s);

    Spell getById(int id);

    Spell getByName(String name);

    List getAll();

    boolean contains(String name);
}
