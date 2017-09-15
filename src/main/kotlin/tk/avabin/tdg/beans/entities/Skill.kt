package tk.avabin.tdg.beans.entities

import lombok.NoArgsConstructor
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component
import javax.persistence.*
import javax.validation.constraints.NotNull

/**
 * Created by Avabin on 10.04.2017.
 */
@Component
@Scope("prototype")
@NoArgsConstructor
@Entity
@Table(name = "skill")
data class Skill(
        @Id
        @GeneratedValue
        @NotNull
        var id: Int = 0,

        @NotNull
        @Column(unique = true)
        var name: String = "",

        @NotNull
        var bonusAttributePosition: Int = 0,

        var rank: Int = 0,

        var otherBonuses: Int = 0

)
