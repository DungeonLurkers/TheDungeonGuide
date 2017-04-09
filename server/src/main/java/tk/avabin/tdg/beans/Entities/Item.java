package tk.avabin.tdg.beans.Entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;

/**
 * Created by Avabin on 09.04.2017.
 */
@Component
@Data
@NoArgsConstructor
@Entity
@Table(name = "item")
public class Item {
    @Id
    @Column(name = "item_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "item_name", nullable = false)
    private String name;

    @Column(name = "item_desc")
    private String desc;

    @Column(name = "item_price", nullable = false)
    private long price;

}
