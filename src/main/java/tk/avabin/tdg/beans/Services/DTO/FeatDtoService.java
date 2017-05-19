package tk.avabin.tdg.beans.Services.DTO;

import tk.avabin.tdg.beans.DTO.FeatDto;
import tk.avabin.tdg.beans.Entities.Feat;

/**
 * Created by Avabin on 18.05.2017.
 */
public interface FeatDtoService {
    Feat dtoToEntity(FeatDto dto);

    FeatDto objectToDto(Feat entity);
}
