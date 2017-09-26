package tk.avabin.tdg.beans.dtos

import com.fasterxml.jackson.annotation.JsonIgnore
import tk.avabin.tdg.util.Role
import java.io.Serializable

data class UserRoleDto(
    var id: Int = 0,
    @JsonIgnore
    private var _role: Role = Role.ROLE_NONE
) : Serializable {
    var role: String = _role.value
        get() = _role.value
        set(value) {
            _role = Role.valueOf(value)
            field = value
        }
}