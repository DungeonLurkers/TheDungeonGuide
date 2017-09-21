package tk.avabin.tdg.beans.services.entities;

import tk.avabin.tdg.beans.entities.User;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Avabin on 09.04.2017.
 */
@Transactional
public interface UserService {
    User saveOrUpdate(User u);

    void delete(User u);

    User getById(int id);

    User getByUsername(String username);

    List getAll();

    boolean contains(String name);
}
