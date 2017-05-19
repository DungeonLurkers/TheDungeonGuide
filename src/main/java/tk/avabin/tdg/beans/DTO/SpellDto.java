package tk.avabin.tdg.beans.DTO;

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
public class SpellDto implements Serializable {
    private int id;
    private String name;
    private String desc;
    private short rank;
}
