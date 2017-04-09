package tk.avabin.tdg.beans.Entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Avabin on 13.03.2017.
 */
@Component
@Data
@NoArgsConstructor
@Entity
@Table(name = "game_character")
public class Character {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "character_id", nullable = false, unique = true)
    private long id;

    @Column(name = "character_name", nullable = false)
    private String name;

    @ManyToOne
    private User owner;

    @ManyToOne
    private RPGSession session;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Item> items;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Spell> spells;
}
