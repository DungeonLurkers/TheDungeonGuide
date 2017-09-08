package tk.avabin.tdg;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.SQLException;

@RunWith(SpringRunner.class)
public class PostgresIntegrationTest {

    @Autowired
    private DataSource dataSource;

    @Test
    public void testConnection() {
        try {
            assert (dataSource.getConnection() != null);
            assert (dataSource.getConnection().isValid(10));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
