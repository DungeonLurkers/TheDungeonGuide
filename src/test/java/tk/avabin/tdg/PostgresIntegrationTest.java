package tk.avabin.tdg;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class PostgresIntegrationTest {

    @Autowired
    private SessionFactory sessionFactory;

    @Test
    public void testConnection() {
        Session session = sessionFactory.getCurrentSession();
        assert (session.isConnected());
    }
}
