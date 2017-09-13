package tk.avabin.tdg.beans.services.Entities;

import tk.avabin.tdg.beans.entities.Language;

import java.util.List;

/**
 * Created by Avabin on 09.04.2017.
 */
public interface LanguageService {
    void saveOrUpdate(Language c);

    void delete(Language c);

    Language getById(int id);

    Language getByName(String name);

    List getAll();
}
