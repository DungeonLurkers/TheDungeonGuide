package tk.avabin.tdg.beans.Entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;

/**
 * Created by Avabin on 03.04.2017.
 */
@Data
@NoArgsConstructor
public class RPGSession implements Serializable {
    private int id;
    private String name;
    private User gameMaster;
    private Set<Character> Characters;
    private Set<User> players;
}
