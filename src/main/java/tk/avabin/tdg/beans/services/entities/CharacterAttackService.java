package tk.avabin.tdg.beans.services.entities;

import tk.avabin.tdg.beans.entities.CharacterAttack;

import java.util.List;

/**
 * Created by Avabin on 09.04.2017.
 */
public interface CharacterAttackService {
    void saveOrUpdate(CharacterAttack c);

    void delete(CharacterAttack c);

    CharacterAttack getById(int id);

    List getAll();
}
