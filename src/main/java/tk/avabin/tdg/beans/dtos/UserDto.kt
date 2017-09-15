package tk.avabin.tdg.beans.dtos

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
        var username: String = "",
        var email: String = "",
        @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
        var password: String = "",
        var salt: String = ""
) : Serializable