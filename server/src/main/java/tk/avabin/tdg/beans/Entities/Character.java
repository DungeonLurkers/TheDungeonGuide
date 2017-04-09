package tk.avabin.tdg.beans.Entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * Created by Avabin on 13.03.2017.
 */
@Component
@Data
@NoArgsConstructor
@Entity
public class Character {
    @Id
    @GeneratedValue
    @NotEmpty
    private Long id;
    private String name;
    @ManyToOne(fetch = FetchType.EAGER)
    private User owner;
    @OneToMany(targetEntity = Item.class, fetch = FetchType.EAGER)
    private List<Item> items;
    @OneToMany(targetEntity = Spell.class, fetch = FetchType.EAGER)
    private Set<Spell> spells;
}
