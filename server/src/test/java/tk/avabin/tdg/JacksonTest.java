package tk.avabin.tdg;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.Test;
import tk.avabin.tdg.beans.Entities.User;

import java.io.IOException;

/**
 * Created by avabin on 24.04.17.
 */
public class JacksonTest {
    @Test
    public void jacksonTest() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        User u = new User();
        u.setId(1);
        u.setUsername("Admin");
        u.setEmail("test@gmail.com");
        mapper.writeValue(System.out, u);
    }
}
