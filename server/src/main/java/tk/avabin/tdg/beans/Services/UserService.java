package tk.avabin.tdg.beans.Services;

import tk.avabin.tdg.beans.Entities.User;

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
