package tk.avabin.tdg.beans.services.dtos;

import tk.avabin.tdg.beans.dtos.RPGClassAndLevelDto;
import tk.avabin.tdg.beans.entities.RPGClassAndLevel;

/**
 * Created by Avabin on 18.05.2017.
 */
public interface RPGClassAndLevelDtoService {
    RPGClassAndLevel dtoToEntity(RPGClassAndLevelDto dto);

    RPGClassAndLevelDto objectToDto(RPGClassAndLevel entity);
}
