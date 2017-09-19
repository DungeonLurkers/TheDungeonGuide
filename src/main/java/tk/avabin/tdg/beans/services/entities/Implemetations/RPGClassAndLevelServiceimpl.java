package tk.avabin.tdg.beans.services.entities.Implemetations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.avabin.tdg.beans.entities.RPGClassAndLevel;
import tk.avabin.tdg.beans.repositories.RPGClassAndLevelRepository;
import tk.avabin.tdg.beans.services.entities.RPGClassAndLevelService;

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
    public RPGClassAndLevel saveOrUpdate(RPGClassAndLevel c) {
        return repository.save(c);
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

    @Override
    public boolean contains(String name) {
        try {
            return repository.findRPGClassAndLevelByRpgClass(name) != null;
        } catch (Exception e) {
            return false;
        }
    }
}
