package tk.avabin.tdg.beans.Entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Avabin on 10.04.2017.
 */
@Component
@Scope("prototype")
@Data
@NoArgsConstructor
@Entity
@Table(name = "rpg_class")
public class RPGClassAndLevel implements Serializable {
    @ManyToOne
    private Character character;
    private String rpgClass;
    private Integer level;

    @Id
    @GeneratedValue
    private String id;
}
