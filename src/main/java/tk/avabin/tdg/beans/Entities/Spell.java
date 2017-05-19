package tk.avabin.tdg.beans.Entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.*;

/**
 * Created by Avabin on 09.04.2017.
 */
@Component
@Scope("prototype")
@Data
@NoArgsConstructor
@Entity
@Table(name = "spell")
public class Spell {
    @Id
    @Column(name = "spell_id", nullable = false)
    @GeneratedValue
    private int id;

    @Column(name = "spell_name", nullable = false, unique = true)
    private String name;

    @Column(name = "spell_desc")
    private String desc;

    @Column(name = "rank")
    private short rank;
}
