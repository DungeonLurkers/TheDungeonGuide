package tk.avabin.tdg.beans.Entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created by Avabin on 09.04.2017.
 */
@Data
@NoArgsConstructor
public class Spell implements Serializable {
    private int id;
    private String name;
    private String desc;
    private short rank;
}
