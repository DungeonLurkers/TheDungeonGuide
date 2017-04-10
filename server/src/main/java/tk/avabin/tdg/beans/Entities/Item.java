package tk.avabin.tdg.beans.Entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Avabin on 09.04.2017.
 */
@Component
@Scope("prototype")
@Data
@NoArgsConstructor
@Entity
@Table(name = "item")
public class Item implements Serializable {
    @Id
    @Column(name = "item_id", nullable = false)
    @GeneratedValue
    private int id;

    @Column(name = "item_name", nullable = false)
    private String name;

    @Column(name = "item_desc")
    private String desc;

    @Column(name = "item_price", nullable = false)
    private long price;

}
