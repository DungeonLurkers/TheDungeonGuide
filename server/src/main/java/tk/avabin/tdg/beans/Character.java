package tk.avabin.tdg.beans;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * Created by Avabin on 13.03.2017.
 */
@Component
@Data
public class Character {
    private Long id;
    private String name;
}
