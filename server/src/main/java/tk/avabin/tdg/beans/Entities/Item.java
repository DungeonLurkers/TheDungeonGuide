package tk.avabin.tdg.beans.Entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by Avabin on 09.04.2017.
 */
@Component
@Data
@NoArgsConstructor
@Entity
public class Item {
    @Id
    @GeneratedValue
    @NotEmpty
    private Long id;
    private String name;
    private int price;
    private String desc;
}
