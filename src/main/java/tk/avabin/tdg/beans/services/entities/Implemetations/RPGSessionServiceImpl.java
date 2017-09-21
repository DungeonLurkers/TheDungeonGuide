package tk.avabin.tdg.beans.services.entities.Implemetations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.avabin.tdg.beans.entities.RPGSession;
import tk.avabin.tdg.beans.repositories.RPGSessionRepository;
import tk.avabin.tdg.beans.services.entities.RPGSessionService;

import java.util.List;

/**
 * Created by Avabin on 09.04.2017.
 */
@Service
public class RPGSessionServiceImpl implements RPGSessionService {
    private final RPGSessionRepository repository;

    @Autowired
    public RPGSessionServiceImpl(RPGSessionRepository repository) {
        this.repository = repository;
    }

    @Override
    public RPGSession saveOrUpdate(RPGSession r) {
        return repository.save(r);
    }

    @Override
    public void delete(RPGSession r) {
        repository.delete(r);
    }

    @Override
    public RPGSession getById(int id) {
        return repository.findOne(id);
    }

    @Override
    public RPGSession getByName(String name) {
        return repository.findRPGSessionByName(name);
    }

    @Override
    public List getAll() {
        return repository.findAll();
    }

    @Override
    public boolean contains(String name) {
        try {
            return repository.findRPGSessionByName(name) != null;
        } catch (Exception e) {
            return false;
        }
    }
}
