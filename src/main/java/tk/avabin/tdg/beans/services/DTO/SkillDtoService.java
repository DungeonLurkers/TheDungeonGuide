package tk.avabin.tdg.beans.services.DTO;

import tk.avabin.tdg.beans.dtos.SkillDto;
import tk.avabin.tdg.beans.entities.Skill;

/**
 * Created by Avabin on 18.05.2017.
 */
public interface SkillDtoService {
    Skill dtoToEntity(SkillDto dto);

    SkillDto objectToDto(Skill entity);
}
