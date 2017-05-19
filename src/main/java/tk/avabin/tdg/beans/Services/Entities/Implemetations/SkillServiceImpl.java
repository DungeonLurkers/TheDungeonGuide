package tk.avabin.tdg.beans.Services.Entities.Implemetations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.avabin.tdg.beans.Entities.Skill;
import tk.avabin.tdg.beans.Repositories.SkillRepository;
import tk.avabin.tdg.beans.Services.Entities.SkillService;

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
    public void saveOrUpdate(Skill c) {
        repository.save(c);
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
}
