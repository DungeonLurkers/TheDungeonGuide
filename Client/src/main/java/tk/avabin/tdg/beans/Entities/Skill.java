package tk.avabin.tdg.beans.Entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created by Avabin on 10.04.2017.
 */
@Data
@NoArgsConstructor
public class Skill implements Serializable {
    private Integer id;
    private String name;
    private Integer bonusAttributePosition;
    private Integer rank;
    private Integer otherBonuses;
}
