package tk.avabin.tdg.beans.services.dtos;

import tk.avabin.tdg.beans.dtos.SpellDto;
import tk.avabin.tdg.beans.entities.Spell;

/**
 * Created by Avabin on 18.05.2017.
 */
public interface SpellDtoService {
    Spell dtoToEntity(SpellDto dto);

    SpellDto objectToDto(Spell entity);
}
