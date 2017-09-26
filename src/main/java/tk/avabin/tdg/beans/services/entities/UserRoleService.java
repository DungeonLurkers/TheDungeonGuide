package tk.avabin.tdg.beans.services.entities;

import tk.avabin.tdg.beans.entities.UserRole;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface UserRoleService {
    UserRole saveOrUpdate(UserRole s);

    void delete(UserRole s);

    UserRole getById(int id);

    List getAll();

}
