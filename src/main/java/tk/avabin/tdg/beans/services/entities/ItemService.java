package tk.avabin.tdg.beans.services.entities;

import tk.avabin.tdg.beans.entities.Item;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Avabin on 09.04.2017.
 */
@Transactional
public interface ItemService extends IEntityService {
    Item saveOrUpdate(Item i);

    void delete(Item i);

    Item getById(int id);

    Item getByName(String name);

    @SuppressWarnings("SameReturnValue")
    List getAll();

    boolean contains(String name);
}
