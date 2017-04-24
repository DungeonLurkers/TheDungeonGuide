package tk.avabin.tdg.beans.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.avabin.tdg.beans.Entities.Character;
import tk.avabin.tdg.beans.Entities.User;
import tk.avabin.tdg.beans.Repositories.CharacterRepository;

import java.util.List;

/**
 * Created by Avabin on 09.04.2017.
 */
@Service
public class CharacterServiceImpl implements CharacterService {
    private final CharacterRepository repository;

    @Autowired
    public CharacterServiceImpl(CharacterRepository repository) {
        this.repository = repository;
    }

    @Override
    public void saveOrUpdate(Character c) {
        repository.save(c);
    }

    @Override
    public void delete(Character c) {
        repository.delete(c);
    }

    @Override
    public Character getById(int id) {
        return repository.findOne(id);
    }

    @Override
    public Character getByName(String name) {
        return repository.findCharacterByName(name);
    }

    @Override
    public Character getByOwner(User owner) {
        return repository.findCharacterByOwner(owner);
    }

    @Override
    public List findAllByName(String name) {
        return repository.findAllByName(name);
    }

    @Override
    public List getAll() {
        return repository.findAll();
    }
}
