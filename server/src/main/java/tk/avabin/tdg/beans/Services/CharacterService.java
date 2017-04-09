package tk.avabin.tdg.beans.Services;

import tk.avabin.tdg.beans.Entities.Character;

import java.util.List;

/**
 * Created by Avabin on 09.04.2017.
 */
public interface CharacterService {
    void saveOrUpdate(Character c);

    void delete(Character c);

    Character getById(int id);

    List getAll();
}
