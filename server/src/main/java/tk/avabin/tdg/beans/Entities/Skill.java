package tk.avabin.tdg.beans.Entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by Avabin on 10.04.2017.
 */
@Component
@Scope("prototype")
@Data
@NoArgsConstructor
@Entity
@Table(name = "skill")
public class Skill implements Serializable {
    @Id
    @GeneratedValue
    @NotNull
    private Integer id;

    @NotNull
    private String name;

    @NotNull
    private Integer bonusAttributePosition;

    private Integer rank;

    private Integer otherBonuses;

}
