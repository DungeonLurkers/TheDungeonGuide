package tk.avabin.tdg.beans.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tk.avabin.tdg.beans.Entities.User;

/**
 * Created by Avabin on 09.04.2017.
 */
public interface UserRepository extends JpaRepository<User, Integer> {
    User findUserByUsername(String username);
}
