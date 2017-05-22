package tk.avabin.tdg.beans.DTO;

import lombok.Data;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * Created by Avabin on 18.05.2017.
 */
@Component
@Data
public class UserDto implements Serializable {
    private int id;
    private String name;
    private String email;
    @JsonIgnore
    private String password;
    private String salt;
}
