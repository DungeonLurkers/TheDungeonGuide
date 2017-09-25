package tk.avabin.tdg.beans.entities

import lombok.NoArgsConstructor
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

/**
 * Created by Avabin on 09.04.2017.
 */
@Component
@Scope("prototype")
@NoArgsConstructor
@Entity
@Table(name = "spell")
data class Spell(
    @Id
    @Column(name = "spell_id", nullable = false)
    @GeneratedValue
    var id: Int = 0,

    @Column(name = "spell_name", nullable = false, unique = true)
    var name: String = "",

    @Column(name = "spell_desc")
    var desc: String = "",

    @Column(name = "rank")
    var rank: Short = 0
)
