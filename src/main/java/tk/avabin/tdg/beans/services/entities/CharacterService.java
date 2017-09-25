package tk.avabin.tdg.beans.services.entities;

import tk.avabin.tdg.beans.entities.Character;
import tk.avabin.tdg.beans.entities.User;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Avabin on 09.04.2017.
 */
@Transactional
public interface CharacterService {
    Character saveOrUpdate(Character c);

    void delete(Character c);

    Character getById(int id);

    Character getByName(String name);

    List<Character> getAllByOwner(User owner);

    List getAll();

    boolean contains(String name);
}
