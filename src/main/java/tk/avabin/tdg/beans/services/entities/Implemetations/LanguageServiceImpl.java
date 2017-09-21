package tk.avabin.tdg.beans.services.entities.Implemetations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.avabin.tdg.beans.entities.Language;
import tk.avabin.tdg.beans.repositories.LanguageRepository;
import tk.avabin.tdg.beans.services.entities.LanguageService;

import java.util.List;

/**
 * Created by Avabin on 10.04.2017.
 */
@Service
public class LanguageServiceImpl implements LanguageService {
    private final LanguageRepository repository;

    @Autowired
    public LanguageServiceImpl(LanguageRepository repository) {
        this.repository = repository;
    }

    @Override
    public Language saveOrUpdate(Language c) {
        return repository.save(c);
    }

    @Override
    public void delete(Language c) {
        repository.delete(c);
    }

    @Override
    public Language getById(int id) {
        return repository.findOne(id);
    }

    @Override
    public Language getByName(String name) {
        return repository.findLanguageByName(name);
    }

    @Override
    public List getAll() {
        return repository.findAll();
    }

    @Override
    public boolean contains(String name) {
        try {
            return repository.findLanguageByName(name) != null;
        } catch (Exception e) {
            return false;
        }
    }
}
