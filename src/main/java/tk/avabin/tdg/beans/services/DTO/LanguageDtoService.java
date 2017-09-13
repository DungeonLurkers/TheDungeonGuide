package tk.avabin.tdg.beans.services.DTO;

import tk.avabin.tdg.beans.dtos.LanguageDto;
import tk.avabin.tdg.beans.entities.Language;

/**
 * Created by Avabin on 18.05.2017.
 */
public interface LanguageDtoService {
    Language dtoToEntity(LanguageDto dto);

    LanguageDto objectToDto(Language entity);
}
