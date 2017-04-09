package tk.avabin.tdg.beans.Entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Avabin on 03.04.2017.
 */
@Component
@Data
@NoArgsConstructor
@Entity
public class RPGSession {
    @Id
    @GeneratedValue
    @NotEmpty
    private Long id;
    private String name;
    @ManyToOne
    private User gameMaster;
    @OneToMany(targetEntity = Character.class, fetch = FetchType.EAGER)
    private Set<Character> characters;
    @OneToMany(targetEntity = User.class, fetch = FetchType.EAGER)
    private Set<User> players;
}
