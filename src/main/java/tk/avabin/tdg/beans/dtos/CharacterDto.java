package tk.avabin.tdg.beans.dtos;

import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Set;

/**
 * Created by Avabin on 18.05.2017.
 */
@Component
@Data
@Scope("prototype")
public class CharacterDto implements Serializable {
    private int id;
    private String name;
    private UserDto owner;
    private Set<RPGClassAndLevelDto> classesAndLevels;
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
    private String baseAttackBonus;
    private Integer spellResistance;
    private Integer armorClass;
    private Integer strength;
    private Integer dexterity;
    private Integer constitution;
    private Integer intelligence;
    private Integer wisdom;
    private Integer charisma;
    private Set<ItemDto> items;
    private Set<SpellDto> spells;
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
    private Set<SkillDto> skills;
    private Set<FeatDto> feats;
    private Set<LanguageDto> languages;
}
