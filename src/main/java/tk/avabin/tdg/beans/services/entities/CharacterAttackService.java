package tk.avabin.tdg.beans.services.entities;

import tk.avabin.tdg.beans.entities.CharacterAttack;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Avabin on 09.04.2017.
 */
@Transactional
public interface CharacterAttackService {
    CharacterAttack saveOrUpdate(CharacterAttack c);

    void delete(CharacterAttack c);

    CharacterAttack getById(int id);

    CharacterAttack getByName(String name);

    List getAll();

    boolean contains(String name);
}
