package tk.avabin.tdg.beans.Services.Entities.Implemetations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.avabin.tdg.beans.Entities.RPGSession;
import tk.avabin.tdg.beans.Repositories.RPGSessionRepository;
import tk.avabin.tdg.beans.Services.Entities.RPGSessionService;

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
    public void saveOrUpdate(RPGSession r) {
        repository.save(r);
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
}
