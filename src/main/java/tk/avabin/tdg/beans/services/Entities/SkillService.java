package tk.avabin.tdg.beans.services.Entities;

import tk.avabin.tdg.beans.entities.Skill;

import java.util.List;

/**
 * Created by Avabin on 09.04.2017.
 */
public interface SkillService {
    void saveOrUpdate(Skill c);

    void delete(Skill c);

    Skill getById(int id);

    Skill getByName(String name);

    List getAll();
}
