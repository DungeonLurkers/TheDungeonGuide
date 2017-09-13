package tk.avabin.tdg.beans.services.DTO;

import tk.avabin.tdg.beans.dtos.ItemDto;
import tk.avabin.tdg.beans.entities.Item;

/**
 * Created by Avabin on 18.05.2017.
 */
public interface ItemDtoService {
    Item dtoToEntity(ItemDto dto);

    ItemDto objectToDto(Item entity);
}
