package tk.avabin.tdg.beans.services.entities.Implemetations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.avabin.tdg.beans.entities.CharacterAttack;
import tk.avabin.tdg.beans.repositories.CharacterAttackRepository;
import tk.avabin.tdg.beans.services.entities.CharacterAttackService;

import java.util.List;

/**
 * Created by Avabin on 09.04.2017.
 */
@Service
public class CharacterAttackServiceImpl implements CharacterAttackService {
    @Autowired
    private final CharacterAttackRepository repository;

    public CharacterAttackServiceImpl(CharacterAttackRepository repository) {
        this.repository = repository;
    }


    @Override
    public CharacterAttack saveOrUpdate(CharacterAttack c) {
        return repository.save(c);
    }

    @Override
    public void delete(CharacterAttack c) {
        repository.delete(c);
    }

    @Override
    public CharacterAttack getById(int id) {
        return repository.findOne(id);
    }

    @Override
    public CharacterAttack getByName(String name) {
        return repository.findCharacterAttackByName(name);
    }

    @Override
    public List getAll() {
        return null;
    }

    @Override
    public boolean contains(String name) {
        try {
            return repository.findCharacterAttackByName(name) != null;
        } catch (Exception e) {
            return false;
        }
    }
}
