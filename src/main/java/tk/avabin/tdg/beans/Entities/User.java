package tk.avabin.tdg.beans.Entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Avabin on 13.03.2017.
 */
@Component
@Scope("prototype")
@Data
@NoArgsConstructor
@Entity
@Table(name = "app_user")
public class User implements Serializable {

    @Id
    @Column(name = "user_id", nullable = false)
    @GeneratedValue
    private int id;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "salt", nullable = false)
    private String salt;
}
