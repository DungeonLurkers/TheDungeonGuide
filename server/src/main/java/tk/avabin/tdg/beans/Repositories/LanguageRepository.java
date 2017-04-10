package tk.avabin.tdg.beans.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tk.avabin.tdg.beans.Entities.Language;

/**
 * Created by Avabin on 10.04.2017.
 */
public interface LanguageRepository extends JpaRepository<Language, Integer> {
    Language findLanguageByName(String name);
}
