package tk.avabin.tdg.beans.Services.DTO;

import tk.avabin.tdg.beans.DTO.CharacterDto;
import tk.avabin.tdg.beans.Entities.Character;

/**
 * Created by Avabin on 18.05.2017.
 */
public interface CharacterDtoService {
    Character dtoToEntity(CharacterDto dto);

    CharacterDto objectToDto(Character entity);
}
