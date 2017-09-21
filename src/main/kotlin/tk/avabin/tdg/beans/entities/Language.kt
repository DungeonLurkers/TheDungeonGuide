package tk.avabin.tdg.beans.entities

import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

/**
 * Created by Avabin on 10.04.2017.
 */
@Component
@Scope("prototype")
@Entity
@Table(name = "language")
data class Language(
        @Id
        var id: Int = 0,
        var name: String = ""
)
