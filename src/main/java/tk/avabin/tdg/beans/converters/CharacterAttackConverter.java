package tk.avabin.tdg.beans.converters;

import org.modelmapper.AbstractConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tk.avabin.tdg.beans.dtos.CharacterAttackDto;
import tk.avabin.tdg.beans.entities.CharacterAttack;

/**
 * Created by avabin on 22.05.17.
 */
@Component
public class CharacterAttackConverter extends AbstractConverter<CharacterAttack, CharacterAttackDto> {
    @Autowired
    private CharacterAttackDto dto;

    @Override
    protected CharacterAttackDto convert(CharacterAttack source) {
        if (source.getOwner() != null)
            dto.setOwnerId(source.getOwner().getId());
        if (source.getAttackItem() != null)
            dto.setAttackItemId(source.getAttackItem().getId());

        return dto;
    }
}
