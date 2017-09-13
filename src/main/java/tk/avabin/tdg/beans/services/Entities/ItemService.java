package tk.avabin.tdg.beans.services.Entities;

import tk.avabin.tdg.beans.entities.Item;

import java.util.List;

/**
 * Created by Avabin on 09.04.2017.
 */
public interface ItemService {
    void saveOrUpdate(Item i);

    void delete(Item i);

    Item getById(int id);

    Item getByName(String name);

    List getAll();
}
