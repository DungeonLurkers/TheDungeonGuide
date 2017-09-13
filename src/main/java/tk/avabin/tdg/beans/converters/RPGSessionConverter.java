package tk.avabin.tdg.beans.converters;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.modelmapper.AbstractConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tk.avabin.tdg.beans.dtos.RPGSessionDto;
import tk.avabin.tdg.beans.entities.Character;
import tk.avabin.tdg.beans.entities.RPGSession;
import tk.avabin.tdg.beans.entities.User;

/**
 * Created by Avabin on 19.05.2017.
 */
@Component
@Data
@EqualsAndHashCode(callSuper = false)
public class RPGSessionConverter extends AbstractConverter<RPGSession, RPGSessionDto> {
    @Autowired
    private RPGSessionDto dto;

    @Override
    protected RPGSessionDto convert(RPGSession source) {
        if (source.getName() != null)
            dto.setName(source.getName());
        if (source.getGameMaster() != null)
            dto.setGameMasterId(source.getGameMaster().getId());
        if (source.getCharacters() != null)
            dto.setCharactersIds(source.getCharacters().stream().mapToInt(Character::getId).toArray());
        if (source.getPlayers() != null)
            dto.setPlayersIds(source.getPlayers().stream().mapToInt(User::getId).toArray());
        return dto;
    }
}
