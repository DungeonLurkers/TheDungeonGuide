package tk.avabin.tdg.beans.entities

import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.ManyToOne
import javax.persistence.Table

/**
 * Created by Avabin on 10.04.2017.
 */
@Component
@Scope("prototype")
@Entity
@Table(name = "character_attack")
data class CharacterAttack(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Int = 0,
        var name: String = "",
        @ManyToOne
        var attackItem: Item? = null,
        @Column(name = "attack_bonus")
        var attackBonus: Int = 0,
        @Column(length = 20)
        var damage: String = "",
        @Column(length = 10)
        var crit: String = "",
        var range: Int = 0,
        @Column(length = 40)
        var type: String = "",
        var notes: String = "",
        @Column(length = 30)
        var ammunition: String = "",
        @Column(name = "ammunition_count")
        var ammunitionCount: Int = 0
)