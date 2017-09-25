package tk.avabin.tdg.beans.entities

import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.ManyToOne
import javax.persistence.OneToMany
import javax.persistence.Table

/**
 * Created by Avabin on 13.03.2017.
 */
@Component
@Scope("prototype")
@Entity
@Table(name = "game_character")
data class Character(
        @Id
        @GeneratedValue
        @Column(name = "character_id", nullable = false, unique = true)
        var id: Int = 0,
        @Column(name = "character_name", nullable = false, unique = true)
        var name: String = "",
        @ManyToOne
        var owner: User? = null,
        @OneToMany(cascade = arrayOf(CascadeType.ALL))
        var classesAndLevels: Set<RPGClassAndLevel>? = HashSet(),
        var race: String = "",
        var alignment: String = "",
        var deity: String = "",
        var size: String = "",
        var age: Int = 0,
        var gender: String = "",
        var height: Int = 0,
        var weight: Int = 0,
        var eyes: Int = 0,
        var hair: Int = 0,
        var skin: Int = 0,
        @Column(name = "hit_dice", length = 5)
        var hitDice: String = "",
        var hitPoints: Int = 0,
        var initiative: Int = 0,
        var speed: Int = 0,
        @Column(name = "base_attack_bonus")
        var baseAttackBonus: String = "",
        @Column(name = "spell_resistance")
        var spellResistance: Int = 0,
        @Column(name = "armor_class")
        var armorClass: Int = 0,
        var strength: Int = 0,
        var dexterity: Int = 0,
        var constitution: Int = 0,
        var intelligence: Int = 0,
        var wisdom: Int = 0,
        var charisma: Int = 0,
        @OneToMany(fetch = FetchType.EAGER, cascade = arrayOf(CascadeType.ALL))
        var items: Set<Item> = HashSet(),
        @OneToMany(fetch = FetchType.EAGER, cascade = arrayOf(CascadeType.ALL))
        var spells: Set<Spell> = HashSet(),
        var rank0SpellsPerDay: Int = 0,
        var rank1SpellsPerDay: Int = 0,
        var rank2SpellsPerDay: Int = 0,
        var rank3SpellsPerDay: Int = 0,
        var rank4SpellsPerDay: Int = 0,
        var rank5SpellsPerDay: Int = 0,
        var rank6SpellsPerDay: Int = 0,
        var rank7SpellsPerDay: Int = 0,
        var rank8SpellsPerDay: Int = 0,
        var rank9SpellsPerDay: Int = 0,
        @OneToMany(fetch = FetchType.EAGER, cascade = arrayOf(CascadeType.ALL))
        var skills: Set<Skill> = HashSet(),
        @OneToMany(fetch = FetchType.EAGER, cascade = arrayOf(CascadeType.ALL))
        var feats: Set<Feat> = HashSet(),
        @OneToMany(fetch = FetchType.EAGER, cascade = arrayOf(CascadeType.ALL))
        var languages: Set<Language> = HashSet()
)