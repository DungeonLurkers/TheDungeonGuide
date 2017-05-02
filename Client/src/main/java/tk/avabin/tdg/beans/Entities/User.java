package tk.avabin.tdg.beans.Entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created by Avabin on 13.03.2017.
 */
@Data
@NoArgsConstructor
public class User implements Serializable {
    private int id;
    private String username;
    private String email;
    private String password;
    private String salt;
}
