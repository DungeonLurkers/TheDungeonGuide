package tk.avabin.tdg.beans.Converters;

import org.modelmapper.AbstractConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tk.avabin.tdg.beans.DTO.RPGClassAndLevelDto;
import tk.avabin.tdg.beans.Entities.RPGClassAndLevel;

/**
 * @author Avabin
 */
@Component
public class RPGClassAndLevelConverter extends AbstractConverter<RPGClassAndLevel, RPGClassAndLevelDto> {

    private final RPGClassAndLevelDto dto;

    @Autowired
    public RPGClassAndLevelConverter(RPGClassAndLevelDto dto) {
        this.dto = dto;
    }

    @Override
    protected RPGClassAndLevelDto convert(RPGClassAndLevel source) {
        if (source.getCharacter() != null)
            dto.setCharacterId(source.getCharacter().getId());
        return null;
    }
}
