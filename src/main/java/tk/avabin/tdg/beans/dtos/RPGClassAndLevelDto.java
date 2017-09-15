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
public class RPGClassAndLevelDto implements Serializable {
    private int id;
    private String rpgClass;
    private Integer level;

}
