package tk.avabin.tdg.beans.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tk.avabin.tdg.beans.Entities.Item;

/**
 * Created by Avabin on 09.04.2017.
 */
public interface ItemRepository extends JpaRepository<Item, Integer> {
    Item findItemByName(String name);
}
