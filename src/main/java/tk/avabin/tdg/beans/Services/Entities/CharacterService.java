package tk.avabin.tdg.beans.Services.Entities;

import tk.avabin.tdg.beans.Entities.Character;
import tk.avabin.tdg.beans.Entities.User;

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
