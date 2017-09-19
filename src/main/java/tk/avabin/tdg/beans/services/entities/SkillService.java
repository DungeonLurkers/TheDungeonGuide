package tk.avabin.tdg.beans.services.entities;

import tk.avabin.tdg.beans.entities.Skill;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Avabin on 09.04.2017.
 */
@Transactional
public interface SkillService {
    Skill saveOrUpdate(Skill c);

    void delete(Skill c);

    Skill getById(int id);

    Skill getByName(String name);

    List getAll();

    boolean contains(String name);
}
