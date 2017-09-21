package tk.avabin.tdg.beans.dtos

import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component
import java.io.Serializable

/**
 * Created by Avabin on 18.05.2017.
 */
@Component
@Scope("prototype")
data class CharacterAttackDto(
        var id: Int = 0,
        var name: String = "",
        var attackItem: ItemDto? = null,
        var attackBonus: Int = 0,
        var damage: String = "",
        var crit: String = "",
        var range: Int = 0,
        var type: String = "",
        var notes: String = "",
        var ammunition: String = "",
        var ammunitionCount: Int = 0
) : Serializable