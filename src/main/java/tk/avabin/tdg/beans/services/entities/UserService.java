package tk.avabin.tdg.beans.services.entities;

import tk.avabin.tdg.beans.entities.User;

import java.util.List;

/**
 * Created by Avabin on 09.04.2017.
 */
public interface UserService {
    void saveOrUpdate(User u);

    void delete(User u);

    User getById(int id);

    User getByUsername(String username);

    List getAll();
}
