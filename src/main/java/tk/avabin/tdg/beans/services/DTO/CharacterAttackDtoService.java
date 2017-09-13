package tk.avabin.tdg.beans.services.DTO;

import tk.avabin.tdg.beans.dtos.CharacterAttackDto;
import tk.avabin.tdg.beans.entities.CharacterAttack;

/**
 * Created by Avabin on 18.05.2017.
 */
public interface CharacterAttackDtoService {
    CharacterAttack dtoToEntity(CharacterAttackDto dto);

    CharacterAttackDto entityToDto(CharacterAttack entity);
}
