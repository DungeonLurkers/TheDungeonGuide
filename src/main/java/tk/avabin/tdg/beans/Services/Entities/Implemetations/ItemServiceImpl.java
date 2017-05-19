package tk.avabin.tdg.beans.Services.Entities.Implemetations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.avabin.tdg.beans.Entities.Item;
import tk.avabin.tdg.beans.Repositories.ItemRepository;
import tk.avabin.tdg.beans.Services.Entities.ItemService;

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
    public void saveOrUpdate(Item i) {
        repository.save(i);
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
}
