package tk.avabin.tdg.beans.Services.DTO;

import tk.avabin.tdg.beans.DTO.SkillDto;
import tk.avabin.tdg.beans.Entities.Skill;

/**
 * Created by Avabin on 18.05.2017.
 */
public interface SkillDtoService {
    Skill dtoToEntity(SkillDto dto);

    SkillDto objectToDto(Skill entity);
}
