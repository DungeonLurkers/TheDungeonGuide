package tk.avabin.tdg.beans.DTO;

import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by Avabin on 18.05.2017.
 */
@Data
@Component
@Scope("prototype")
public class RPGSessionDto {
    private int id;
    private String name;
    private int gameMasterId;
    private int[] CharactersIds;
    private int[] playersIds;
}
