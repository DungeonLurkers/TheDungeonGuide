package tk.avabin.tdg.beans.services.entities.Implemetations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.avabin.tdg.beans.entities.Skill;
import tk.avabin.tdg.beans.repositories.SkillRepository;
import tk.avabin.tdg.beans.services.entities.SkillService;

import java.util.List;

/**
 * Created by Avabin on 09.04.2017.
 */
@Service
public class SkillServiceImpl implements SkillService {
    private final SkillRepository repository;

    @Autowired
    public SkillServiceImpl(SkillRepository repository) {
        this.repository = repository;
    }

    @Override
    public Skill saveOrUpdate(Skill c) {
        return repository.save(c);
    }

    @Override
    public void delete(Skill c) {
        repository.delete(c);
    }

    @Override
    public Skill getById(int id) {
        return repository.findOne(id);
    }

    @Override
    public Skill getByName(String name) {
        return repository.findSkillByName(name);
    }

    @Override
    public List getAll() {
        return repository.findAll();
    }

    @Override
    public boolean contains(String name) {
        try {
            return repository.findSkillByName(name) != null;
        } catch (Exception e) {
            return false;
        }
    }
}
