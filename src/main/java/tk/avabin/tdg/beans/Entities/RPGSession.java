package tk.avabin.tdg.beans.Entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by Avabin on 03.04.2017.
 */
@Component
@Scope("prototype")
@Data
@NoArgsConstructor
@Entity
public class RPGSession implements Serializable {
    @Id
    @GeneratedValue
    @NotNull
    private int id;
    private String name;
    @ManyToOne
    private User gameMaster;
    @OneToMany(targetEntity = Character.class, fetch = FetchType.EAGER)
    private Set<Character> Characters;
    @ManyToMany(targetEntity = User.class, fetch = FetchType.EAGER)
    private Set<User> players;
}