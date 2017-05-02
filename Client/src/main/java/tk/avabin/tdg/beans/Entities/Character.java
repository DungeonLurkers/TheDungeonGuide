package tk.avabin.tdg.beans.Entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;

/**
 * Created by Avabin on 13.03.2017.
 */
@Data
@NoArgsConstructor
public class Character implements Serializable {
    private int id;
    private String name;
    private User owner;
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
    private String hitDice;
    private Integer hitPoints;
    private Integer initiative;
    private Integer speed;
    private Integer baseAttackBonus;
    private Integer spellResistance;
    private Integer armorClass;
    private Integer strength;
    private Integer dexterity;
    private Integer constitution;
    private Integer intelligence;
    private Integer wisdom;
    private Integer charisma;
    private Set<Item> items;
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
    private Set<Skill> skills;
    private Set<Feat> feats;
    private Set<Language> languages;
}
