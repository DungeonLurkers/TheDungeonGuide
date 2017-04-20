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
@Table(name = "game_feat")
public class Feat implements Serializable {
    @Id
    @GeneratedValue
    private Integer id;
    @Column(length = 70)
    private String name;
    @Column(name = "feat_desc")
    private String desc;
}
