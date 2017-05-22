package tk.avabin.tdg.beans.DTO;

import lombok.Data;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @author Avabin
 */
@Component
@Data
@Scope("prototype")
public class UserDto implements Serializable {
    private int id;
    private String username;
    private String email;
    @JsonIgnore
    private String password;
    private String salt;
}
