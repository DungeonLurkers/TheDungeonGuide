package tk.avabin.tdg.beans.dtos;

import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * Created by Avabin on 18.05.2017.
 */
@Data
@Component
@Scope("prototype")
public class RPGSessionDto {
    private int id;
    private String name;
    private UserDto gameMaster;
    private Set<CharacterDto> characters;
    private Set<UserDto> players;
}
