package tk.avabin.tdg.beans.Services.DTO;

import tk.avabin.tdg.beans.DTO.CharacterAttackDto;
import tk.avabin.tdg.beans.Entities.CharacterAttack;

/**
 * Created by Avabin on 18.05.2017.
 */
public interface CharacterAttackDtoService {
    CharacterAttack dtoToEntity(CharacterAttackDto dto);

    CharacterAttackDto entityToDto(CharacterAttack entity);
}
