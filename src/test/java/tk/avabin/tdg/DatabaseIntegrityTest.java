package tk.avabin.tdg;

import org.junit.Before;
import org.junit.Test;
import org.postgresql.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by avabin on 02.05.17.
 */
public class DatabaseIntegrityTest {
    private String databaseURL;

    @Before
    public void beforeDatabaseTest() {
        databaseURL = System.getenv("DATABASE_URL_T");
    }

    @Test
    public void databaseTest() {
        try {
            Driver driver = new Driver();
            DriverManager.registerDriver(driver);

            Connection conn = DriverManager.getConnection(databaseURL);
            conn.close();
        } catch (SQLException e) {
            System.err.println("Could not load driver!");
            System.exit(1);
        }
    }
}
