package tk.avabin.tdg.beans.Services.DTO;

import tk.avabin.tdg.beans.DTO.ItemDto;
import tk.avabin.tdg.beans.Entities.Item;

/**
 * Created by Avabin on 18.05.2017.
 */
public interface ItemDtoService {
    Item dtoToEntity(ItemDto dto);

    ItemDto objectToDto(Item entity);
}
