package tk.avabin.tdg.beans.entities

import tk.avabin.tdg.util.Role
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "user_role")
data class UserRole(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    var id: Int = 0,
    private var _role: Role = Role.ROLE_NONE
) {
    var role: String = _role.value
        get() = _role.value
        set(value) {
            _role = Role.valueOf(value)
            field = value
        }
}