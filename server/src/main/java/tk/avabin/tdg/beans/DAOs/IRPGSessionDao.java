package tk.avabin.tdg.beans.DAOs;

import org.springframework.data.jpa.repository.JpaRepository;
import tk.avabin.tdg.beans.Entities.RPGSession;

/**
 * Created by Avabin on 09.04.2017.
 */
public interface IRPGSessionDao extends JpaRepository<RPGSession, Integer> {
}
