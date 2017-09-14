package tk.avabin.tdg.beans.services.dtos;

import tk.avabin.tdg.beans.dtos.FeatDto;
import tk.avabin.tdg.beans.entities.Feat;

/**
 * Created by Avabin on 18.05.2017.
 */
public interface FeatDtoService {
    Feat dtoToEntity(FeatDto dto);

    FeatDto objectToDto(Feat entity);
}
