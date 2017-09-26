package tk.avabin.tdg.beans.services.entities.Implemetations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.avabin.tdg.beans.entities.UserRole;
import tk.avabin.tdg.beans.repositories.UserRoleRepository;
import tk.avabin.tdg.beans.services.entities.UserRoleService;

import java.util.List;

@Service
public class UserRoleServiceImpl implements UserRoleService {
    @Autowired
    private UserRoleRepository repository;

    @Override
    public UserRole saveOrUpdate(UserRole s) {
        return repository.save(s);
    }

    @Override
    public void delete(UserRole s) {
        repository.delete(s);
    }

    @Override
    public UserRole getById(int id) {
        return repository.findOne(id);
    }

    @Override
    public List getAll() {
        return repository.findAll();
    }

}
