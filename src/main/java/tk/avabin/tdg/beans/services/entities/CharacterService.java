package tk.avabin.tdg.beans.services.entities;

import tk.avabin.tdg.beans.entities.Character;
import tk.avabin.tdg.beans.entities.User;

import java.util.List;

/**
 * Created by Avabin on 09.04.2017.
 */
public interface CharacterService {
    void saveOrUpdate(Character c);

    void delete(Character c);

    Character getById(int id);

    Character getByName(String name);

    Character getAllByOwner(User owner);

    List getAll();
}
