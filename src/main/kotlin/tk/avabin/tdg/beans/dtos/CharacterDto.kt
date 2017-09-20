package tk.avabin.tdg.beans.dtos

import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component
import java.io.Serializable

/**
 * Created by Avabin on 18.05.2017.
 */
@Component
@Scope("prototype")
data class CharacterDto(
        var id: Int = 0,
        var name: String = "",
        var owner: UserDto? = null,
        var classesAndLevels: Set<RPGClassAndLevelDto> = HashSet(),
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
        var hitDice: String = "",
        var hitPoints: Int = 0,
        var initiative: Int = 0,
        var speed: Int = 0,
        var baseAttackBonus: String = "",
        var spellResistance: Int = 0,
        var armorClass: Int = 0,
        var strength: Int = 0,
        var dexterity: Int = 0,
        var constitution: Int = 0,
        var intelligence: Int = 0,
        var wisdom: Int = 0,
        var charisma: Int = 0,
        var items: Set<ItemDto> = HashSet(),
        var spells: Set<SpellDto> = HashSet(),
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
        var skills: Set<SkillDto> = HashSet(),
        var feats: Set<FeatDto> = HashSet(),
        var languages: Set<LanguageDto> = HashSet()
) : Serializable