package tk.avabin.tdg;

import org.junit.Test;
import tk.avabin.tdg.beans.Entities.Character;
import tk.avabin.tdg.beans.Entities.User;

import java.util.HashSet;

/**
 * Created by avabi on 13.03.2017.
 */
public class UtilTest {
    @Test
    public void prettyStringFromObjectTest() {
        User u = new User();
        u.setId(1);
        u.setUsername("Admin");
        u.setEmail("test@gmail.com");
        System.out.println(Util.prettyStringFromObject(u));
    }
}
