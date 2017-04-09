package tk.avabin.tdg.beans.Entities;

import lombok.Data;
import lombok.NoArgsConstructor;
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
@Table(name = "app_user")
public class User {

    @Id
    @Column(name = "user_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @OneToMany(targetEntity = Character.class, fetch = FetchType.EAGER, mappedBy = "owner")
    private Set<Character> characters;

    @ManyToMany(targetEntity = RPGSession.class, fetch = FetchType.EAGER)
    private Set<RPGSession> sessions;
}
