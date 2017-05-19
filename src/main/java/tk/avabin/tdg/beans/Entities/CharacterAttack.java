package tk.avabin.tdg.beans.Entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.*;

/**
 * Created by Avabin on 10.04.2017.
 */
@Component
@Scope("prototype")
@Data
@NoArgsConstructor
@Entity
@Table(name = "character_attack")
public class CharacterAttack {
    @Id
    @GeneratedValue
    private Integer id;
    @ManyToOne
    private Character owner;
    @ManyToOne
    private Item attackItem;
    @Column(name = "attack_bonus")
    private Integer attackBonus;
    @Column(length = 20)
    private String damage;
    @Column(length = 10)
    private String crit;
    private Integer range;
    @Column(length = 40)
    private String type;
    private String notes;
    @Column(length = 30)
    private String ammunition;
    @Column(name = "ammunition_count")
    private Integer ammunitionCount;
}
