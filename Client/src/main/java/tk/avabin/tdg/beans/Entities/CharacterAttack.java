package tk.avabin.tdg.beans.Entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created by Avabin on 10.04.2017.
 */
@Data
@NoArgsConstructor
public class CharacterAttack implements Serializable {
    private Integer id;
    private Character owner;
    private Item attackItem;
    private Integer attackBonus;
    private String damage;
    private String crit;
    private Integer range;
    private String type;
    private String notes;
    private String ammunition;
    private Integer ammunitionCount;
}
