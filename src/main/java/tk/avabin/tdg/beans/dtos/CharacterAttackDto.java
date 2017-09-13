package tk.avabin.tdg.beans.dtos;

import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * Created by Avabin on 18.05.2017.
 */
@Component
@Data
@Scope("prototype")
public class CharacterAttackDto implements Serializable {
    private Integer id;
    private int ownerId;
    private int attackItemId;
    private Integer attackBonus;
    private String damage;
    private String crit;
    private Integer range;
    private String type;
    private String notes;
    private String ammunition;
    private Integer ammunitionCount;
}
