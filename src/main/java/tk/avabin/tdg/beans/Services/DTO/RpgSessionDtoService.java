package tk.avabin.tdg.beans.Services.DTO;

import tk.avabin.tdg.beans.DTO.RPGSessionDto;
import tk.avabin.tdg.beans.Entities.RPGSession;

/**
 * Created by Avabin on 18.05.2017.
 */
public interface RpgSessionDtoService {
    RPGSession dtoToEntity(RPGSessionDto dto);

    RPGSessionDto objectToDto(RPGSession entity);
}
