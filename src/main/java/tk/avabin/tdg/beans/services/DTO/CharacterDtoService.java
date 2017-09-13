package tk.avabin.tdg.beans.services.DTO;

import tk.avabin.tdg.beans.dtos.CharacterDto;
import tk.avabin.tdg.beans.entities.Character;

/**
 * Created by Avabin on 18.05.2017.
 */
public interface CharacterDtoService {
    Character dtoToEntity(CharacterDto dto);

    CharacterDto objectToDto(Character entity);
}
