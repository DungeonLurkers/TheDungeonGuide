package tk.avabin.tdg.beans.services.entities;

import tk.avabin.tdg.beans.entities.CharacterAttack;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Avabin on 09.04.2017.
 */
@Transactional
public interface CharacterAttackService extends IEntityService {
    CharacterAttack saveOrUpdate(CharacterAttack c);

    void delete(CharacterAttack c);

    CharacterAttack getById(int id);

    CharacterAttack getByName(String name);

    @SuppressWarnings("SameReturnValue")
    List getAll();

    boolean contains(String name);
}
