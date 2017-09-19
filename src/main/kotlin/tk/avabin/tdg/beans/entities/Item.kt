package tk.avabin.tdg.beans.entities

import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component
import javax.persistence.*

/**
 * Created by Avabin on 09.04.2017.
 */
@Component
@Scope("prototype")
@Entity
@Table(name = "item")
data class Item(
        @Id
        @Column(name = "item_id", nullable = false)
        @GeneratedValue
        var id: Int = 0,

        @Column(name = "item_name", nullable = false)
        var name: String = "",

        @Column(name = "item_desc")
        var desc: String = "",

        @Column(name = "item_price", nullable = false)
        var price: Long = 0

)