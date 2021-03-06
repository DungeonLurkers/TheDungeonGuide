package tk.avabin.tdg.beans.services.entities.Implemetations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.avabin.tdg.beans.entities.Character;
import tk.avabin.tdg.beans.entities.User;
import tk.avabin.tdg.beans.repositories.CharacterRepository;
import tk.avabin.tdg.beans.services.entities.CharacterService;

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
    public Character saveOrUpdate(Character c) {
        return repository.save(c);
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
    public List<Character> getAllByOwner(User owner) {
        return repository.findAllByOwner(owner);
    }

    @Override
    public List getAll() {
        return repository.findAll();
    }

    @Override
    public boolean contains(String name) {
        try {
            return repository.findCharacterByName(name) != null;
        } catch (Exception e) {
            return false;
        }
    }
}
