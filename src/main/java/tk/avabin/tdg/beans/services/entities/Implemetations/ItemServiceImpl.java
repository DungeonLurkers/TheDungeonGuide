package tk.avabin.tdg.beans.services.entities.Implemetations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.avabin.tdg.beans.entities.Item;
import tk.avabin.tdg.beans.repositories.ItemRepository;
import tk.avabin.tdg.beans.services.entities.ItemService;

import java.util.List;

/**
 * Created by Avabin on 09.04.2017.
 */
@Service
public class ItemServiceImpl implements ItemService {
    private final ItemRepository repository;

    @Autowired
    public ItemServiceImpl(ItemRepository repository) {
        this.repository = repository;
    }

    @Override
    public Item saveOrUpdate(Item i) {
        return repository.save(i);
    }

    @Override
    public void delete(Item i) {
        repository.delete(i);
    }

    @Override
    public Item getById(int id) {
        return repository.findOne(id);
    }

    @Override
    public Item getByName(String name) {
        return repository.findItemByName(name);
    }

    @Override
    public List getAll() {
        return null;
    }

    @Override
    public boolean contains(String name) {
        try {
            return repository.findItemByName(name) != null;
        } catch (Exception e) {
            return false;
        }
    }
}
