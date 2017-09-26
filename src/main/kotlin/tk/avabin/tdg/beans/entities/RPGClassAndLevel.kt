package tk.avabin.tdg.beans.entities

import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

/**
 * Created by Avabin on 10.04.2017.
 */
@Component
@Scope("prototype")
@Entity
@Table(name = "rpg_class")
data class RPGClassAndLevel(
        var rpgClass: String = "",
        var level: Int = 0,

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Int = 0
)