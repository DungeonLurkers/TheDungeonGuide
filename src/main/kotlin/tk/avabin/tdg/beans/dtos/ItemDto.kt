package tk.avabin.tdg.beans.dtos

import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component
import java.io.Serializable

/**
 * Created by Avabin on 18.05.2017.
 */
@Component
@Scope("prototype")
class ItemDto(
        var id: Int = 0,
        var name: String = "",
        var desc: String = "",
        var price: Long = 0
) : Serializable