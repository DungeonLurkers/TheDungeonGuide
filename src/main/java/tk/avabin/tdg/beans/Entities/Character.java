package tk.avabin.tdg.beans.Entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Avabin on 13.03.2017.
 */
@Component
@Scope("prototype")
@Data
@NoArgsConstructor
@Entity
@Table(name = "game_character")
public class Character {
    @Id
    @GeneratedValue
    @Column(name = "character_id", nullable = false, unique = true)
    private int id;
    @Column(name = "character_name", nullable = false, unique = true)
    private String name;
    @ManyToOne
    private User owner;
    @OneToMany
    private Set<RPGClassAndLevel> classesAndLevels;
    private String race;
    private String alignment;
    private String deity;
    private String size;
    private Integer age;
    private String gender;
    private Integer height;
    private Integer weight;
    private Integer eyes;
    private Integer hair;
    private Integer skin;
    @Column(name = "hit_dice", length = 5)
    private String hitDice;
    private Integer hitPoints;
    private Integer initiative;
    private Integer speed;
    @Column(name = "base_attack_bonus")
    private String baseAttackBonus;
    @Column(name = "spell_resistance")
    private Integer spellResistance;
    @Column(name = "armor_class")
    private Integer armorClass;
    private Integer strength;
    private Integer dexterity;
    private Integer constitution;
    private Integer intelligence;
    private Integer wisdom;
    private Integer charisma;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Item> items;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Spell> spells;
    private Integer rank0SpellsPerDay;
    private Integer rank1SpellsPerDay;
    private Integer rank2SpellsPerDay;
    private Integer rank3SpellsPerDay;
    private Integer rank4SpellsPerDay;
    private Integer rank5SpellsPerDay;
    private Integer rank6SpellsPerDay;
    private Integer rank7SpellsPerDay;
    private Integer rank8SpellsPerDay;
    private Integer rank9SpellsPerDay;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Skill> skills;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Feat> feats;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Language> languages;
}
