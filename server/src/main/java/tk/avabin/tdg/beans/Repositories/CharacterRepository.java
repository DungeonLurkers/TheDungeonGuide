package tk.avabin.tdg.beans.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tk.avabin.tdg.beans.Entities.Character;
import tk.avabin.tdg.beans.Entities.User;

/**
 * Created by Avabin on 09.04.2017.
 */
public interface CharacterRepository extends JpaRepository<Character, Integer> {
    Character findCharacterByName(String name);

    Character findCharacterByOwner(User owner);
}
