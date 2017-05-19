package tk.avabin.tdg.beans.Converters;

import lombok.Data;
import org.modelmapper.AbstractConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tk.avabin.tdg.beans.DTO.RPGSessionDto;
import tk.avabin.tdg.beans.Entities.Character;
import tk.avabin.tdg.beans.Entities.RPGSession;
import tk.avabin.tdg.beans.Entities.User;

/**
 * Created by Avabin on 19.05.2017.
 */
@Component
@Data
public class RPGSessionConverter extends AbstractConverter<RPGSession, RPGSessionDto> {
    @Autowired
    private RPGSessionDto dto;

    @Override
    protected RPGSessionDto convert(RPGSession source) {
        if (source.getGameMaster() != null)
            dto.setGameMasterId(source.getGameMaster().getId());
        if (!source.getCharacters().isEmpty())
            dto.setCharactersIds(source.getCharacters().stream().mapToInt(Character::getId).toArray());
        if (!source.getPlayers().isEmpty())
            dto.setPlayersIds(source.getPlayers().stream().mapToInt(User::getId).toArray());
        return dto;
    }
}
