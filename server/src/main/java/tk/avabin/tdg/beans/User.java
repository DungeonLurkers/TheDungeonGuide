package tk.avabin.tdg.beans;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * Created by Avabin on 13.03.2017.
 */
@Component
@Data
public class User {
    private Long id;
    private String username;
    private String email;
    private Set<Character> characters;
}
