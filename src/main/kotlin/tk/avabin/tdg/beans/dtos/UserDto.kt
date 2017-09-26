package tk.avabin.tdg.beans.dtos

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import lombok.NoArgsConstructor
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component
import java.io.Serializable

/**
 * @author Avabin
 */
@NoArgsConstructor
@Component
@Scope("prototype")
data class UserDto(
        var id: Int = 0,
    var name: String = "",
        private val _password: String = "",
        var email: String = "",
    var salt: String = "",
    @JsonProperty(required = false)
    var roles: Set<UserRoleDto> = HashSet()
) : Serializable {
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    var password: String = _password
        @JsonIgnore
        get() {
            return field
        }
}