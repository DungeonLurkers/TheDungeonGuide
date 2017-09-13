package tk.avabin.tdg.beans.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tk.avabin.tdg.beans.entities.Spell;

/**
 * Created by Avabin on 09.04.2017.
 */
public interface SpellRepository extends JpaRepository<Spell, Integer> {
    Spell findSpellByName(String name);
}
