package tk.avabin.tdg.beans.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tk.avabin.tdg.beans.entities.Language;

/**
 * Created by Avabin on 10.04.2017.
 */
public interface LanguageRepository extends JpaRepository<Language, Integer> {
    Language findLanguageByName(String name);
}
