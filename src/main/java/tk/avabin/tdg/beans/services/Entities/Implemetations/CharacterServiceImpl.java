package tk.avabin.tdg.beans.services.Entities.Implemetations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.avabin.tdg.beans.entities.Character;
import tk.avabin.tdg.beans.entities.User;
import tk.avabin.tdg.beans.repositories.CharacterRepository;
import tk.avabin.tdg.beans.services.Entities.CharacterService;

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
    public Character getAllByOwner(User owner) {
        return repository.findAllByOwner(owner);
    }

    @Override
    public List getAll() {
        return repository.findAll();
    }
}
