package tk.avabin.tdg.beans.Services.DTO;

import tk.avabin.tdg.beans.DTO.LanguageDto;
import tk.avabin.tdg.beans.Entities.Language;

/**
 * Created by Avabin on 18.05.2017.
 */
public interface LanguageDtoService {
    Language dtoToEntity(LanguageDto dto);

    LanguageDto objectToDto(Language entity);
}
