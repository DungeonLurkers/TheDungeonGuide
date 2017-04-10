package tk.avabin.tdg.beans.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.avabin.tdg.beans.Entities.CharacterAttack;
import tk.avabin.tdg.beans.Repositories.CharacterAttackRepository;

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
    public void saveOrUpdate(CharacterAttack c) {
        repository.save(c);
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
    public List getAll() {
        return null;
    }
}
