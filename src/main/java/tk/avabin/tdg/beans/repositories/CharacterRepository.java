package tk.avabin.tdg.beans.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tk.avabin.tdg.beans.entities.Character;
import tk.avabin.tdg.beans.entities.User;

import java.util.List;

/**
 * Created by Avabin on 09.04.2017.
 */
public interface CharacterRepository extends JpaRepository<Character, Integer> {
    Character findCharacterByName(String name);

    List<Character> findAllByOwner(User owner);
}
