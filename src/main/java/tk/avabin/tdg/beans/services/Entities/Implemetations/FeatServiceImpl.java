package tk.avabin.tdg.beans.services.Entities.Implemetations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.avabin.tdg.beans.entities.Feat;
import tk.avabin.tdg.beans.repositories.FeatRepository;
import tk.avabin.tdg.beans.services.Entities.FeatService;

import java.util.List;

/**
 * Created by Avabin on 09.04.2017.
 */
@Service
public class FeatServiceImpl implements FeatService {
    private final FeatRepository repository;

    @Autowired
    public FeatServiceImpl(FeatRepository repository) {
        this.repository = repository;
    }

    @Override
    public void saveOrUpdate(Feat c) {
        repository.save(c);
    }

    @Override
    public void delete(Feat c) {
        repository.delete(c);
    }

    @Override
    public Feat getById(int id) {
        return repository.findOne(id);
    }

    @Override
    public Feat getByName(String name) {
        return repository.findFeatByName(name);
    }

    @Override
    public List getAll() {
        return repository.findAll();
    }
}
