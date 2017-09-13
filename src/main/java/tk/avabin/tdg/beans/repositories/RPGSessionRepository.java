package tk.avabin.tdg.beans.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tk.avabin.tdg.beans.entities.RPGSession;

/**
 * Created by Avabin on 09.04.2017.
 */
public interface RPGSessionRepository extends JpaRepository<RPGSession, Integer> {
    RPGSession findRPGSessionByName(String name);
}
