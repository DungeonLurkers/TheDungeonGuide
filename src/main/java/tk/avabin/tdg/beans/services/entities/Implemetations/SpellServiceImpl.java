package tk.avabin.tdg.beans.services.entities.Implemetations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.avabin.tdg.beans.entities.Spell;
import tk.avabin.tdg.beans.repositories.SpellRepository;
import tk.avabin.tdg.beans.services.entities.SpellService;

import java.util.List;

/**
 * Created by Avabin on 09.04.2017.
 */
@Service
public class SpellServiceImpl implements SpellService {
    private final SpellRepository repository;

    @Autowired
    public SpellServiceImpl(SpellRepository repository) {
        this.repository = repository;
    }

    @Override
    public void saveOrUpdate(Spell s) {
        repository.save(s);
    }

    @Override
    public void delete(Spell s) {
        repository.delete(s);
    }

    @Override
    public Spell getById(int id) {
        return repository.findOne(id);
    }

    @Override
    public Spell getByName(String name) {
        return repository.findSpellByName(name);
    }

    @Override
    public List getAll() {
        return repository.findAll();
    }
}
