package tk.avabin.tdg.beans.Entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by Avabin on 10.04.2017.
 */
@Component
@Scope("prototype")
@Data
@NoArgsConstructor
@Entity
@Table(name = "language")
public class Language implements Serializable {
    @Id
    private Integer id;
    private String name;
}
