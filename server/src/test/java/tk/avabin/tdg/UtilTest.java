package tk.avabin.tdg;

import org.junit.Test;
import tk.avabin.tdg.beans.Character;
import tk.avabin.tdg.beans.User;

import java.util.HashSet;

/**
 * Created by avabi on 13.03.2017.
 */
public class UtilTest {
    @Test
    public void prettyStringFromObjectTest() {
        User u = new User();
        u.setId(1L);
        u.setUsername("Admin");
        u.setEmail("test@gmail.com");
        System.out.println(Util.prettyStringFromObject(u));
        HashSet<Character> c = new HashSet<>();
        c.add(new Character());
        u.setCharacters(c);
        System.out.println(Util.prettyStringFromObject(u));
        Character ca = new Character();
        ca.setId(1L);
        ca.setName("Test");
        c.add(ca);
        System.out.println(Util.prettyStringFromObject(u));
    }
}
