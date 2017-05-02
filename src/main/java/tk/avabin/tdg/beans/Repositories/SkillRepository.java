package tk.avabin.tdg.beans.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tk.avabin.tdg.beans.Entities.Skill;

/**
 * Created by Avabin on 10.04.2017.
 */
public interface SkillRepository extends JpaRepository<Skill, Integer> {
    Skill findSkillByName(String name);
}
