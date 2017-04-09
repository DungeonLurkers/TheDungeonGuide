package tk.avabin.tdg.beans.Entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Avabin on 13.03.2017.
 */
@Component
@Data
@NoArgsConstructor
@Entity
public class User {
    @GeneratedValue
    @Id
    @NotEmpty
    private Long id;
    private String username;
    private String email;
    private String password;
    @OneToMany(targetEntity = Character.class, fetch = FetchType.EAGER)
    private Set<Character> characters;
}
