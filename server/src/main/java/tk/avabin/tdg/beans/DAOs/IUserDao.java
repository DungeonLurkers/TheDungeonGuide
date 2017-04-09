package tk.avabin.tdg.beans.DAOs;

import org.springframework.data.jpa.repository.JpaRepository;
import tk.avabin.tdg.beans.Entities.User;

/**
 * Created by Avabin on 09.04.2017.
 */
public interface IUserDao extends JpaRepository<User, Integer> {
}
