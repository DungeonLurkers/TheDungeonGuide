package tk.avabin.tdg.beans.services.entities.Implemetations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.avabin.tdg.beans.entities.User;
import tk.avabin.tdg.beans.repositories.UserRepository;
import tk.avabin.tdg.beans.services.entities.UserService;

import java.util.List;

/**
 * Created by Avabin on 09.04.2017.
 */
@Service
public class UserServiceImpl implements UserService {
    final UserRepository repository;

    @Autowired
    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User saveOrUpdate(User u) {
        return repository.save(u);
    }

    @Override
    public void delete(User u) {
        repository.delete(u);
    }

    @Override
    public User getById(int id) {
        return repository.findOne(id);
    }

    @Override
    public User getByUsername(String username) {
        return repository.findUserByUsername(username);
    }

    @Override
    public List<User> getAll() {
        return repository.findAll();
    }

    @Override
    public boolean contains(String name) {
        try {
            return repository.findUserByUsername(name) != null;
        } catch (Exception e) {
            return false;
        }
    }
}
