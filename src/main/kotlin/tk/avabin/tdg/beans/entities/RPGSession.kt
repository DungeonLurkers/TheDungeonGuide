package tk.avabin.tdg.beans.entities

import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.ManyToMany
import javax.persistence.ManyToOne
import javax.persistence.OneToMany
import javax.validation.constraints.NotNull

/**
 * Created by Avabin on 03.04.2017.
 */
@Component
@Scope("prototype")
@Entity
data class RPGSession(
        @Id
        @GeneratedValue
        @NotNull
        var id: Int = 0,
        var name: String = "",
        @ManyToOne
        var gameMaster: User? = null,
        @OneToMany(targetEntity = Character::class, fetch = FetchType.EAGER)
        var characters: Set<Character> = HashSet(),
        @ManyToMany(targetEntity = User::class, fetch = FetchType.EAGER)
        var players: Set<User> = HashSet()
)