package tk.avabin.tdg.beans.dtos

import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component

/**
 * Created by Avabin on 18.05.2017.
 */
@Component
@Scope("prototype")
data class SkillDto(
        var id: Int = 0,
        var name: String = "",
        var bonusAttributePosition: Int = 0,
        var rank: Int = 0,
        var otherBonuses: Int = 0
)