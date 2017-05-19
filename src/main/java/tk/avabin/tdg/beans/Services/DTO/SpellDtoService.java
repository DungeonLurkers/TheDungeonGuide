package tk.avabin.tdg.beans.Services.DTO;

import tk.avabin.tdg.beans.DTO.SpellDto;
import tk.avabin.tdg.beans.Entities.Spell;

/**
 * Created by Avabin on 18.05.2017.
 */
public interface SpellDtoService {
    Spell dtoToEntity(SpellDto dto);

    SpellDto objectToDto(Spell entity);
}
