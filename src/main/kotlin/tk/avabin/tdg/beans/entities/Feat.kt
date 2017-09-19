package tk.avabin.tdg.beans.entities

import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component

import javax.persistence.*

/**
 * Created by Avabin on 10.04.2017.
 */
@Component
@Scope("prototype")
@Entity
@Table(name = "game_feat")
data class Feat(
        @Id
        @GeneratedValue
        var id: Int = 0,
        @Column(length = 70)
        var name: String = "",
        @Column(name = "feat_desc")
        var desc: String = ""
)