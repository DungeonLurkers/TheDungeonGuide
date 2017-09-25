package tk.avabin.tdg.beans.services.entities;

import tk.avabin.tdg.beans.entities.Language;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Avabin on 09.04.2017.
 */
@Transactional
public interface LanguageService extends IEntityService {
    Language saveOrUpdate(Language c);

    void delete(Language c);

    Language getById(int id);

    Language getByName(String name);

    List getAll();

    boolean contains(String name);
}
