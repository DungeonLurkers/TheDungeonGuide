package tk.avabin.tdg.beans.services.dtos;

import tk.avabin.tdg.beans.dtos.RPGSessionDto;
import tk.avabin.tdg.beans.entities.RPGSession;

/**
 * Created by Avabin on 18.05.2017.
 */
public interface RpgSessionDtoService {
    RPGSession dtoToEntity(RPGSessionDto dto);

    RPGSessionDto objectToDto(RPGSession entity);
}
