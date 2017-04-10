package tk.avabin.tdg.beans.Entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created by Avabin on 10.04.2017.
 */
@Data
@NoArgsConstructor
public class Feat implements Serializable {
    private Integer id;
    private String name;
    private String desc;
}
