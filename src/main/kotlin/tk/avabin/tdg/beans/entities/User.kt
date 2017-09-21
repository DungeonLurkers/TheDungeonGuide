package tk.avabin.tdg.beans.entities

import lombok.NoArgsConstructor
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component

import javax.persistence.*

/**
 * Created by Avabin on 13.03.2017.
 */
@Component
@Scope("prototype")
@NoArgsConstructor
@Entity
@Table(name = "app_user")
data class User(

        @Id
    @Column(name = "user_id", nullable = false)
    @GeneratedValue
    var id: Int = 0,

        @Column(name = "username", nullable = false, unique = true)
        var username: String = "",

        @Column(name = "email", nullable = false)
        var email: String = "",

        @Column(name = "password", nullable = false)
        var password: String = "",

        @Column(name = "salt", nullable = false)
        var salt: String = ""
)
