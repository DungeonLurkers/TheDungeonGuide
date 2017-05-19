package tk.avabin.tdg.beans.Services.DTO;

import tk.avabin.tdg.beans.DTO.RPGClassAndLevelDto;
import tk.avabin.tdg.beans.Entities.RPGClassAndLevel;

/**
 * Created by Avabin on 18.05.2017.
 */
public interface RPGClassAndLevelDtoService {
    RPGClassAndLevel dtoToEntity(RPGClassAndLevelDto dto);

    RPGClassAndLevelDto objectToDto(RPGClassAndLevel entity);
}
