package tk.avabin.tdg.beans.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.avabin.tdg.beans.Entities.RPGClassAndLevel;
import tk.avabin.tdg.beans.Repositories.RPGClassAndLevelRepository;

import java.util.List;

/**
 * Created by Avabin on 09.04.2017.
 */
@Service
public class RPGClassAndLevelServiceimpl implements RPGClassAndLevelService {
    private final RPGClassAndLevelRepository repository;

    @Autowired
    public RPGClassAndLevelServiceimpl(RPGClassAndLevelRepository repository) {
        this.repository = repository;
    }

    @Override
    public void saveOrUpdate(RPGClassAndLevel c) {
        repository.save(c);
    }

    @Override
    public void delete(RPGClassAndLevel c) {
        repository.delete(c);
    }

    @Override
    public RPGClassAndLevel getById(int id) {
        return repository.findOne(id);
    }

    @Override
    public List getAll() {
        return repository.findAll();
    }
}
