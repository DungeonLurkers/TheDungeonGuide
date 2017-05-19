package tk.avabin.tdg.beans.Services.Entities.Implemetations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.avabin.tdg.beans.Entities.User;
import tk.avabin.tdg.beans.Repositories.UserRepository;
import tk.avabin.tdg.beans.Services.Entities.UserService;

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
    public void saveOrUpdate(User u) {
        repository.save(u);
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
}
