package tk.avabin.tdg.beans.Services.Implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.avabin.tdg.beans.Entities.Language;
import tk.avabin.tdg.beans.Repositories.LanguageRepository;
import tk.avabin.tdg.beans.Services.LanguageService;

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
    public void saveOrUpdate(Language c) {
        repository.save(c);
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
}
